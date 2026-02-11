package OnlineBookResellingSystem.OBRS_BackEnd.book.ImageUploaderClasses;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImageValidator
{
    private static final long max_size=2*1024*1024;
    private static final List<String> ALLOWED_TYPES=List.of("image/jpeg", "image/png", "image/jpg");

    public void validate(MultipartFile image)
    {
        isNull(image);
        isvalidetype(image);
        isvalidsize(image);
    }

    public void isNull(MultipartFile image)
    {
        if (image==null||image.isEmpty())
        {
            throw new IllegalArgumentException("Image is required");
        }
    }
    public void isvalidetype(MultipartFile image)
    {
        if (!ALLOWED_TYPES.contains(image.getContentType())) {
            throw new IllegalArgumentException("Image is of not Valid type");
        }
    }
    public void isvalidsize(MultipartFile image)
    {
        if (image.getSize() > max_size)
        {
            throw new IllegalArgumentException("Image size must be less than 2MB");
        }
    }

}
