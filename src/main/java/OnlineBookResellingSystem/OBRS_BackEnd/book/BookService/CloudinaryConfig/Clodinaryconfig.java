package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.CloudinaryConfig;


import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Clodinaryconfig
{
    @Value("${c_cName}")
    private String cloud_name;
    @Value("${c_aK}")
    private String apikey;
    @Value("${c_aSk}")
    private String api_secretkey;
    @Bean
    public Cloudinary cloudinary()
    {
        Map config=new HashMap<>();
        config.put("cloud_name", cloud_name);
        config.put("api_key", apikey);
        config.put("api_secret", api_secretkey);
        config.put("secure",true);
        return new Cloudinary(config);
    }
}
