package com.thisara.LNF.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryImageService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                imageFile.getBytes(), // getbyte method is used to convert the file into a byte array, which is required by the Cloudinary API for uploading.
                ObjectUtils.asMap("folder", "lnf/items")
        );
        return uploadResult.get("secure_url").toString();
    }
}
