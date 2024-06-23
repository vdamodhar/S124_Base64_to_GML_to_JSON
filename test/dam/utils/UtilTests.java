package test.dam.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.dam.utils.Utils;

/**
 * UtilTests
 */
public class UtilTests {

    private Path workingDir;
    Utils utils = new Utils();

    @Before
    public void init(){
        this.workingDir = Path.of("","D:\\NC_S100\\Sample-datasets\\S124");
    }

    @Test
    public void testRead() throws Exception{
        this.workingDir = Path.of("","D:\\NC_S100\\Sample-datasets\\S124");
        Path file = this.workingDir.resolve("S124_SECOM.txt");
        String content = Files.readString(file);
        assertEquals((utils.readJSONTextFile("D:\\NC_S100\\Sample-datasets\\S124\\", "S124_SECOM.txt")), content);
    }    
}