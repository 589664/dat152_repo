package database;

import java.util.Objects;

public class Description {
	
	private Integer pNo;
	private String langCode;
	private String text;
	
	public Description() {
	}

	public Description(Integer pNo, String langCode, String text) {
		this.pNo = pNo;
		this.langCode = langCode;
		this.text = text;
	}

	public Integer getpNo() {
		return pNo;
	}

	public void setpNo(Integer pNo) {
		this.pNo = pNo;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Description [pNo=" + pNo + ", langCode=" + langCode + ", text=" + text + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(langCode, pNo, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Description other = (Description) obj;
		return Objects.equals(langCode, other.langCode) && Objects.equals(pNo, other.pNo)
				&& Objects.equals(text, other.text);
	}
}
