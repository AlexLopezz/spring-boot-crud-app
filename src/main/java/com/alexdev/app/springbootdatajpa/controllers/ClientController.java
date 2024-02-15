package com.alexdev.app.springbootdatajpa.controllers;

import com.alexdev.app.springbootdatajpa.models.Client;
import com.alexdev.app.springbootdatajpa.services.IClientService;
import com.alexdev.app.springbootdatajpa.services.IUploadFileService;
import com.alexdev.app.springbootdatajpa.util.Paginator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.Optional;

/**
 *  Esta clase servira como controlador para el manejo del modelo Client, en ella
 *  podemos registrar, modificar, ver, eliminar clientes mediante su clase de negocio.
 */
@Slf4j //Utilidad de Lombok para realizar logs en esta clase.
@SessionAttributes("client")
@Controller //Marcamos esta clase como @Controller lo cual el IoC de Spring lo identificara a la hora de levantar la aplicacion.
public class ClientController {
    //Atributos globales de clase:
    private final IClientService service;
    private final IUploadFileService uploadFileService;

    @Autowired //Inyeccion de dependencia... Aqui el IoC de Spring buscara la clase o bean que instancie esta variable.
    public ClientController(IClientService service, IUploadFileService uploadFileService) {
        this.service = service;
        this.uploadFileService = uploadFileService;
    }

    /**
     * Mostrara un listado paginado de clientes.
     * @param model Servira para pasar atributos a nuestra vista.
     * @param page Numero de pagina que se quiere ver en pantalla.
     * @return Vista HTML correspondiente.
     */
    @GetMapping(value = "/list")
    public String listView(Model model, @RequestParam(defaultValue = "0") int page){
        // Forma de implementar paginacion con Pageable:
        Page<Client> clients = service.findAll(PageRequest.of(page, 5));
        // Instanciamos nuestro paginator, para poder manejarnos en Thymeleaf:
        Paginator<Client> paginator = new Paginator<>("/list", clients);

        log.info("total: " +clients.getTotalElements());
        model.addAttribute("title", "List of clients");
        model.addAttribute("clients", clients);
        model.addAttribute("paginator", paginator);

        return "list";
    }

    /**
     * @param model Servira para pasar atributos a nuestra vista.
     * @return Vista HTML correspondiente.
     */
    @GetMapping("/form")
    public String formView(Model model){
        model.addAttribute("title", "Form Client");
        model.addAttribute("client", new Client());

        return "form";
    }

    /**
     * Mostrara un detalle de cliente la cual se puede modificar y guardar en base de datos.
     * @param model Servira para pasar atributos a nuestra vista.
     * @param idClient  ID unico del cliente registrado en base de datos.
     * @param flashMessage Para indicar mensajes 'flash' en la vista.
     * @return Vista HTML correspondiente
     */
    @GetMapping("/form/{idClient}")
    public String putFormView(Model model,
                              @PathVariable Long idClient,
                              RedirectAttributes flashMessage){
        model.addAttribute("title", "Form Client");

        Optional.ofNullable(idClient)
                .ifPresentOrElse(id -> service.findById(id)
                        .ifPresentOrElse(client -> model.addAttribute("client", client),
                                () -> flashMessage.addFlashAttribute("error", "Client w/ ID("+id+") not found")),
                    () -> {
                        flashMessage.addFlashAttribute("error", "ID Client is required!");
                    });

        return "form";
    }

    /**
     * Mostrara un detalle de cliente en particular.
     * @param idClient ID unico del cliente registrado en base de datos.
     * @param model Servira para pasar atributos a nuestra vista.
     * @param flash Para indicar mensajes 'flash' en la vista.
     * @return Vista HTML correspondiente
     */
    @GetMapping("/show/{id}")
    public String showDetailClient(@PathVariable("id")Long idClient,
                                   Model model,
                                   RedirectAttributes flash){

        Optional.of(idClient)
                .ifPresentOrElse(id -> service.findById(id)
                                .ifPresentOrElse(client -> model.addAttribute("client", client),
                                () -> flash.addFlashAttribute("error", "Client w/ ID("+id+") Not found.")),
                        () -> flash.addFlashAttribute("ID not present.") );

        if(model.getAttribute("client") == null)
            return "redirect:/list";

        model.addAttribute("title", "Client Detail("+idClient+") ");
        return "showDetail.html";
    }

    /**
     *  Guarda / modifica un registro de Cliente en base de datos.
     * @param client Mapeado con los valores del formulario
     * @param binding Es el encargado de verificar si los datos son validos
     * @param model Servira para pasar atributos a nuestra vista.
     * @param flashMessage Para indicar mensajes 'flash' en la vista.
     * @param sessionStatus Para finalizar la session en caso de exito.
     * @param profile Servira para almacenar nuestro archivo en el directorio externo.
     * @return Vista HTML correspondiente.
     */
    @PostMapping("/form")
    public String postFormView(@Valid Client client,
                               BindingResult binding,
                               Model model,
                               RedirectAttributes flashMessage,
                               SessionStatus sessionStatus,
                               @RequestParam(value = "file", required = false) MultipartFile profile){

        if(binding.hasErrors()){
            model.addAttribute("title", "Error! Check values");

            return "form";
        }

        if(!profile.isEmpty()){
            Optional.ofNullable(client.getId())
                    .flatMap(service::findById)
                    .filter(c -> c.getProfile() != null)
                    .ifPresent(c -> uploadFileService.delete(c.getProfile()));

            String filename;
            try {
                filename = uploadFileService.insert(profile);
                log.info("filename: "+filename);
                client.setProfile(filename);
            }catch (IOException ioException){
                throw new RuntimeException(ioException.getMessage());
            }
        }

        service.save(client);
        sessionStatus.setComplete();
        flashMessage.addFlashAttribute("success", "Client save successfully");
        return "redirect:/list";
    }

    /**
     *  Elimina un registro de cliente.
     * @param idClient ID unico del cliente
     * @param flashMessage Servira para indicar un mensaje 'flash' e indicar desde Thymeleaf que se elimino dicho cliente.
     * @return String - a la vista que se redireccionara cuando termine de eliminarse.
     */
    @GetMapping("/delete/{idClient}")
    public String deleteClient(@PathVariable Long idClient,
                               RedirectAttributes flashMessage){

        Optional.ofNullable(idClient)
                .flatMap(service::findById)
                .ifPresent(client -> {
                    if(client.getProfile() != null)
                        uploadFileService.delete(client.getProfile());

                    service.deleteById(idClient);
                    flashMessage.addFlashAttribute("success", "Client delete successfully");
                });

        return "redirect:/list";
    }

    /**
     *  Sirve para descargar un recurso (en este caso una imagen) de nuestra base de datos.
     * @param filename nombre del archivo
     * @return Resource - Recurso a descargar.
     */
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> getProfilePhoto(@PathVariable("filename") String filename) {
        Resource resource;
        try {
            resource = uploadFileService.search(filename);
        }catch (IOException io){ throw new RuntimeException(io.getMessage()); }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * Eliminara tanto los registros de la base de datos como
     * las imagenes del directorio externo.
     * @return Vista HTML correspondiente.
     */
    @GetMapping("/deleteAll")
    public String deleteAllRecords(){
        uploadFileService.deleteAll();
        service.deleteAll();

        return "redirect:/list";
    }
}