package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class LuaFileFilter extends FileFilter {
    private static final String LUA_FILE_EXTENSION = ".lua";
    private static final String LUA_FILE_FILTER_DESCRIPTION = "Lua source File (*.lua)";

    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(LUA_FILE_EXTENSION) || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return LUA_FILE_FILTER_DESCRIPTION;
    }
}
