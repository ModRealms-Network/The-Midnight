package midnight;

import midnight.api.IMidnightInfo;

public final class MidnightInfo implements IMidnightInfo {
    public static final MidnightInfo INSTANCE = new MidnightInfo();

    public static final String MODID = "midnight";
    public static final String NAME = "The Midnight";

    public static final boolean IDE = isRunningFromIDE();

    @DynamicConstant("version")
    public static final String VERSION = "NOT.A.VERSION";

    @DynamicConstant("version_name")
    public static final String VERSION_NAME = "Not A Version";

    @DynamicConstant("sha1")
    public static final String SHA1 = "SHA1";

    @DynamicConstant("build_time")
    public static final String BUILD_DATE = "2038-01-19T03:14:08Z"; // https://en.wikipedia.org/wiki/Year_2038_problem

    private MidnightInfo() {
    }

    private static boolean isRunningFromIDE() {
        String p = System.getProperty("midnight.iside");
        return Boolean.parseBoolean(p);
    }

    @Override
    public String modid() {
        return MODID;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String version() {
        return VERSION;
    }

    @Override
    public String versionName() {
        return VERSION_NAME;
    }

    @Override
    public String buildDate() {
        return BUILD_DATE;
    }

    @Override
    public boolean ide() {
        return IDE;
    }
}
