package com.alexdev.app.springbootdatajpa.services.impl;

import com.alexdev.app.springbootdatajpa.services.IUploadFileService;
import com.alexdev.app.springbootdatajpa.util.UtilPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//Clase de negocio, que servira para administrar las imagenes de nuestro proyecto.
@Service
public class UploadFileService implements IUploadFileService {

    @Autowired private UtilPath utilPath; // Sera de utilidad para obtener nuestro directorio externo.

    @Override
    public Resource search(String filename) throws MalformedURLException {
        Path pathPhoto = Paths.get(utilPath.getRootPath()).resolve(filename);
        Resource resource = new UrlResource(pathPhoto.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathPhoto);
        }

        return resource;
    }

    @Override
    public String insert(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID() +"."+ file.getOriginalFilename();
        Path pathPhoto = Paths.get(utilPath.getRootPath()).resolve(uniqueFilename);

        Files.copy(file.getInputStream(), pathPhoto); // Copia la entrada de bytes a la ruta de directorio especificado.

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        File file = Paths.get(utilPath.getRootPath()).resolve(filename).toFile();

        if(file.exists() && file.canRead())
            return file.delete();

        return false;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(utilPath.getRootPath()).toFile());
        try {
            Files.deleteIfExists(Paths.get(utilPath.getRootPath()));
        }catch (IOException ignored){}
    }

    @Override
    public void init() throws IOException { Files.createDirectory(Paths.get(utilPath.getRootPath())); }
}