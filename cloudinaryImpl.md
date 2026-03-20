### Step-by-Step Explanation of the Image Upload Flow

1. **Frontend File Selection**:
   - When a user selects a file in the Lost Report form, it is stored in `form.imageFile`.
   - This happens in the `ReportLost.jsx` file at line 97.

2. **Multipart Payload Creation**:
   - The selected file is attached to a multipart payload as `imageFile`.
   - The payload is then posted to the `/api/items` endpoint.
   - This occurs in `ReportLost.jsx` at line 105.

3. **Backend Request Handling**:
   - The backend `ItemController` receives the request at line 26.
   - The request contains two parts:
     - A JSON object with item details.
     - An optional `imageFile` if a file was uploaded.

4. **Cloudinary Upload**:
   - In the `createItem` method of `ItemServiceImpl` (line 42), the backend checks if an `imageFile` exists.
   - If it does, the file is sent to Cloudinary for upload.

5. **Cloudinary Service**:
   - The `CloudinaryImageService` handles the upload process.
   - At line 20, the file bytes are sent to Cloudinary using `uploader().upload(...)`.
   - Cloudinary returns a `secure_url` for the uploaded image at line 24.

6. **Database Persistence**:
   - The `secure_url` is saved into the `item.imageUrl` field.
   - This value is persisted in the database under the `image_url` column of the `Item` entity.

7. **Response to Frontend**:
   - The backend returns the `secure_url` as part of the response.
   - This happens in `ItemServiceImpl` at line 127.

8. **Frontend Rendering**:
   - The frontend receives the `secure_url` and uses it to display the uploaded image.
   - The image is rendered using an `<img>` tag with `src={item.imageUrl}`.
   - This occurs in `Index.jsx` at line 373.
