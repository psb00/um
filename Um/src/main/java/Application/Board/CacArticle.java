package Application.Board;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CacArticle {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="file_name")
    private String filename;

    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;

    @Column(name="file_size")
    private Long filesize;



    @Override
    public String toString() {
        String fileType = determineFileType();
        String fileContentString = getFileContentString(fileType);

        return "CacArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", filename='" + filename + '\'' +
                ", fileContent=" + fileContentString +
                ", filesize=" + filesize +"byte";
    }

    private String determineFileType() {
        // 파일 이름에서 확장자 추출
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex + 1);
        }
        return "unknown";
    }

    private String getFileContentString(String fileType) {
        if ("txt".equalsIgnoreCase(fileType)) {
            // 텍스트 파일인 경우
            return new String(fileContent);
        } else if ("jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType)) {
            // JPG 이미지인 경우
            return "JPG image";
        } else {
            // 기타 파일 타입
            return "Binary data";
        }
    }
}
