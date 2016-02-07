package il.co.galex.tools.permissions.util

import org.apache.commons.io.IOUtils

import java.util.jar.JarFile

/**
 * File Utility class for file related stuff.
 *
 * @author Alexander Gherschon
 */
class FileUtils {

    public static final String PREFIX = "PermissionsHelper";
    public static final String SUFFIX = ".java";

    private static InputStream getInputStream(URI uri) {

        def params = uri.toString().split('!')
        def jarPath = params[0]
        def resourcePath = params[1].substring(1)
        def jarFile = new JarFile(jarPath.split(':')[2])
        def jarEntry = jarFile.getEntry(resourcePath)
        return jarFile.getInputStream(jarEntry)
    }

    private static File getResourceFile(InputStream inputStream) throws IOException {

        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(inputStream, out);
        return tempFile
    }

    public static File getFile(URI uri) {

        return getResourceFile(getInputStream(uri))
    }
}