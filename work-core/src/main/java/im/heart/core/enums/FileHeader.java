package im.heart.core.enums;

public enum FileHeader {
	AVI("avi", "41564920","avi格式"),
	BAT("bat", "406563686f206f66660d","avi格式"),
	JAR("jar", "504B03040A0000000000","avi格式"),
	ZIP("zip", "504B0304","zip头格式"),
	JPEG("jpeg", "FFD8FF","JPEG头格式"),
	PNG("png", "89504E47","PNG头格式"),
	BMP("bmp", "424D","BMP头格式"),
	GIF("gif", "47494638","GIF头格式"),
	TIF("tif", "49492A00"," tif头格式"),
	DWG("dwg", "41433130"," dwg头格式 CAD"),
	CHM("chm", "49545346030000006000"),
	CLASSZ("class", "CAFEBABE0000002E0041"),
	DBX("dbx", "CFAD12FEC5FD746F", "Outlook Express(dbx)"),
	RMVB("rmvb", "2E524D46000000120001", ""),
	RTF("rtf", "7B5C727466", "rtf头格式/日记本"),
	RAR("rar", "52617221", "rar头格式"),
	RAM("ram", "2E7261FD", "ram头格式 Real Audio(ram)"),
	PDF("pdf", "255044462D312E", "pdf头格式"),
	TXT("txt", "75736167", "txt头格式"),
	EXE("exe", "4D5A9000", "exe头格式"),
	QDF("qdf", "AC9EBD8F", "Quicken (qdf)"),
	EML("eml", "44656C69766572792D646174653A", ""),
	DLL("dll", "4D5A9000", "dll头格式"),
	DOCX("docx", "504B0304", "docx头格式"),
	DOC("d0cf11e0a1b11ae10000", "docx头格式 MS Excel 注意：word、msi 和 excel的文件头一样"),
	XLS("xls", "D0CF11E0", "xls头格式"),
	GZ("gz", "1F8B08", "gz头格式"),
	MID("mid", "4D546864", "mid头格式"),
	MDB("mdb", "5374616E64617264204A", "mdb头格式 MS Access"),
	MP3("mp3", "49443303000000002176"),
	MP4("mp4", "00000020667479706d70"),
	MOV("mov", "6D6F6F76", "Quicktime (mov)"),
	PS("ps", "252150532D41646F6265"),
	PWL("pwl", "E3828596"),
	XML("xml", "3C3F786D6C"),
	HTML("html", "68746D6C3E"),
	PST("pst", "2142444E"),
	WAV("wav", "57415645"),
	WPD("wpd", "FF575043"),
	WPS("wps", "D0CF11E0A1B11AE10000", "WPS文字wps、表格et、演示dps都是一样的"),
	MXP("mxp", "04000000010000001300"),
	TORRENT("torrent", "6431303A637265617465"),
	VSD("vsd", "D0CF11E0A1B11AE10000"),
	PSD("psd", "38425053", "psd头格式");

	public String code;
	public String value;
	public String desc;
	private FileHeader(String code, String value,String desc) {
		this.code = code;
		this.value = value;
		this.desc = desc;
	}
	private FileHeader(String code, String value) {
		this( code,value,"");
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
