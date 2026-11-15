package dev.nxnative.android;

import java.io.File;

/**
 * A folder dump's control data and other programs on the JVM (dm/tools/tests/test_android_dumpmeta.py): Nacp.read, DumpLayout.controlDir and
 * DumpLayout.programDir. Commands, each printing one line: "nacp &lt;control folder&gt; &lt;language tag&gt;" → title|publisher|version|icon size;
 * "control &lt;dump&gt;" and "program &lt;dump&gt; &lt;n&gt;" → the folder found, relative to the dump ("-" for none)
 */
public final class DumpMetaJvmTest {
    private static String rel(File dump, File f) {
        if (f == null) return "-";
        String d = dump.getAbsolutePath(), p = f.getAbsolutePath();
        return (p.startsWith(d) ? p.substring(d.length() + 1) : p).replace(File.separatorChar, '/');
    }

    public static void main(String[] a) {
        switch (a[0]) {
            case "nacp": {
                Nacp.Control c = Nacp.read(new File(a[1]), a[2]);
                System.out.println(c == null ? "null" : c.title + "|" + c.publisher + "|" + c.version + "|" + (c.icon == null ? -1 : c.icon.length));
                break;
            }
            case "control": System.out.println(rel(new File(a[1]), DumpLayout.controlDir(new File(a[1])))); break;
            case "program": System.out.println(rel(new File(a[1]), DumpLayout.programDir(new File(a[1]), Integer.parseInt(a[2])))); break;
            default: throw new IllegalArgumentException(a[0]);
        }
    }
}
