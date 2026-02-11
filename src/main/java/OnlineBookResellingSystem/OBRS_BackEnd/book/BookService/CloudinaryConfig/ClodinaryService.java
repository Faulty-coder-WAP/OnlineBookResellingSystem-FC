package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.CloudinaryConfig;

import OnlineBookResellingSystem.OBRS_BackEnd.book.ImageUploaderClasses.ImageValidator;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class ClodinaryService
{
    private final Cloudinary cloudinary;
    private final ImageValidator validator;

    public ClodinaryService(Cloudinary cloudinary, ImageValidator validator)
    {
        this.cloudinary = cloudinary;
        this.validator = validator;
    }

    public String  uploadImage(MultipartFile image) throws IOException {
        validator.validate(image);
        Map uploadedresults=cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        return uploadedresults.get("secure_url").toString();
    }
}
