package Application.Board;

import org.springframework.web.multipart.MultipartFile;

import java.beans.ConstructorProperties;
import java.io.IOException;

public class CacArticleForm {
    private Long id;
    private String title;
    private String content;

    private MultipartFile fileData;
    @ConstructorProperties({"id","title","content","fileData"})
    public CacArticleForm(Long id, String title, String content, MultipartFile fileData) {
        this.id = id;

        this.title = title;
        this.content = content;
        this.fileData = fileData;
    }
    public String toString() {
        return "CacArticleForm{" + "id = " + id + " " +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fileData=" + (fileData != null ? fileData.getOriginalFilename() : "null") +
                '}';
    }


    public CacArticle toEntity() {
        CacArticle cacArticle = new CacArticle();
        cacArticle.setId(this.id);
        cacArticle.setTitle(this.title);
        cacArticle.setContent(this.content);

        if (fileData != null && !fileData.isEmpty()) {
            try {
                cacArticle.setFileContent(fileData.getBytes());
                cacArticle.setFilename(fileData.getOriginalFilename());
                cacArticle.setFilesize(fileData.getSize());
            } catch (IOException e) {
                // 예외 처리 필요
                e.printStackTrace();
            }
        } else {
            // 파일 데이터가 없는 경우 기본값 설정
            cacArticle.setFileContent(new byte[0]);
            cacArticle.setFilename("No File");
            cacArticle.setFilesize(0L);
        }

        return cacArticle;
    }

}