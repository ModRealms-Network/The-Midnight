package midnight;

import midnight.api.IMidnightInfo;

public final class MidnightInfo implements IMidnightInfo {
    public static final MidnightInfo INSTANCE = new MidnightInfo();

    // modid will always stay as "midnight"
    public static final String MODID = "midnight";
    // name will always stays as "The Midnight"
    public static final String NAME = "The Midnight";

    // Check if the game is running from an IDE or via Gradle. We have methods in place that use this check.
    public static final boolean IDE = isRunningFromIDE();

    // DO NOT CHANGE! This is changed by Gradle when The Midnight is built.
    @DynamicConstant("version")
    public static final String VERSION = "NOT.A.VERSION";

    // DO NOT CHANGE! This is changed by Gradle when The Midnight is built.
    @DynamicConstant("version_name")
    public static final String VERSION_NAME = "Not A Version";

    // DO NOT CHANGE! This is changed by Gradle when The Midnight is built.
    @DynamicConstant("sha1")
    public static final String SHA1 = "SHA1";

    // DO NOT CHANGE! This is changed by Gradle when The Midnight is build.
    @DynamicConstant("build_time")
    public static final String BUILD_DATE = "2038-01-19T03:14:08Z"; // https://en.wikipedia.org/wiki/Year_2038_problem

    // empty constructor.
    private MidnightInfo() {
    }

    /*
     * The check for IDE is used via a property that is set in build.gradle.
     * This property will never be set if the mod is run normally.
     */
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
