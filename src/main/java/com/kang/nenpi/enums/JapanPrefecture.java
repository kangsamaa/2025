package com.kang.nenpi.enums; // ✅ 패키지명 확인


import java.util.HashMap;
import java.util.Map;

public enum JapanPrefecture {
    HOKKAIDO("Hokkaido", "北海道"),
    AOMORI("Aomori", "青森県"),
    IWATE("Iwate", "岩手県"),
    MIYAGI("Miyagi", "宮城県"),
    AKITA("Akita", "秋田県"),
    YAMAGATA("Yamagata", "山形県"),
    FUKUSHIMA("Fukushima", "福島県"),
    IBARAKI("Ibaraki", "茨城県"),
    TOCHIGI("Tochigi", "栃木県"),
    GUNMA("Gunma", "群馬県"),
    SAITAMA("Saitama", "埼玉県"),
    CHIBA("Chiba", "千葉県"),
    TOKYO("Tokyo", "東京都"),
    KANAGAWA("Kanagawa", "神奈川県"),
    NIIGATA("Niigata", "新潟県"),
    TOYAMA("Toyama", "富山県"),
    ISHIKAWA("Ishikawa", "石川県"),
    FUKUI("Fukui", "福井県"),
    YAMANASHI("Yamanashi", "山梨県"),
    NAGANO("Nagano", "長野県"),
    GIFU("Gifu", "岐阜県"),
    SHIZUOKA("Shizuoka", "静岡県"),
    AICHI("Aichi", "愛知県"),
    MIE("Mie", "三重県"),
    SHIGA("Shiga", "滋賀県"),
    KYOTO("Kyoto", "京都府"),
    OSAKA("Osaka", "大阪府"),
    HYOGO("Hyogo", "兵庫県"),
    NARA("Nara", "奈良県"),
    WAKAYAMA("Wakayama", "和歌山県"),
    TOTTORI("Tottori", "鳥取県"),
    SHIMANE("Shimane", "島根県"),
    OKAYAMA("Okayama", "岡山県"),
    HIROSHIMA("Hiroshima", "広島県"),
    YAMAGUCHI("Yamaguchi", "山口県"),
    TOKUSHIMA("Tokushima", "徳島県"),
    KAGAWA("Kagawa", "香川県"),
    EHIME("Ehime", "愛媛県"),
    KOCHI("Kochi", "高知県"),
    FUKUOKA("Fukuoka", "福岡県"),
    SAGA("Saga", "佐賀県"),
    NAGASAKI("Nagasaki", "長崎県"),
    KUMAMOTO("Kumamoto", "熊本県"),
    OITA("Oita", "大分県"),
    MIYAZAKI("Miyazaki", "宮崎県"),
    KAGOSHIMA("Kagoshima", "鹿児島県"),
    OKINAWA("Okinawa", "沖縄県");

    private static final Map<String, String> englishToKanjiMap = new HashMap<>();
    private static final Map<String, String> kanjiToEnglishMap = new HashMap<>();

    static {
        for (JapanPrefecture prefecture : values()) {
            englishToKanjiMap.put(prefecture.englishName.toLowerCase(), prefecture.kanjiName);
            kanjiToEnglishMap.put(prefecture.kanjiName, prefecture.englishName);
        }
    }

    private final String englishName;
    private final String kanjiName;

    JapanPrefecture(String englishName, String kanjiName) {
        this.englishName = englishName;
        this.kanjiName = kanjiName;
    }

    public static String getKanjiByEnglish(String englishName) {
        return englishToKanjiMap.getOrDefault(englishName.toLowerCase(), "Unknown");
    }

    public static String getEnglishByKanji(String kanjiName) {
        return kanjiToEnglishMap.getOrDefault(kanjiName, "Unknown");
    }
}
