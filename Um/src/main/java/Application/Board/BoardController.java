package Application.Board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class BoardController {
    @Autowired
    CacArticleRepository cacArticleRepository;
    @GetMapping("/article/CacBoard") // 게시글 입력 창
    public String CacBoardView(){
        return "article/CacBoard";
    }
    @PostMapping("/article/created")
    public String CacBoardCreate(CacArticleForm form){
        System.out.println(form.toString());

        CacArticle cacArticle = form.toEntity();

        CacArticle saved = cacArticleRepository.save(cacArticle);

        System.out.println(saved.toString());
        return "redirect:/article/" +saved.getId();
    }

    @GetMapping("/article/{id}")
    public String BoardShow(@PathVariable(name = "id") Long id, Model model){
        log.info("id = " + id);
        CacArticle cacArticleEntity = cacArticleRepository.findById(id).orElse(null);

        model.addAttribute("article", cacArticleEntity);



        return "article/show";
    }

    @GetMapping("/article")
    public String index(Model model) {
        List<CacArticle> cacArticleList =  (List<CacArticle>) cacArticleRepository.findAll();

        model.addAttribute("articleList", cacArticleList);


        return "article/index";
    }
    @GetMapping("/article/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model){
        CacArticle articleEntity = cacArticleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);

        return "article/edit";
    }

    @PostMapping("/article/update")
    public String update(CacArticleForm cacArticleForm){
        log.info(cacArticleForm.toString());
        CacArticle cacArticleEntity = cacArticleForm.toEntity();
        log.info(cacArticleEntity.toString());
        CacArticle target = cacArticleRepository.findById(cacArticleEntity.getId()).orElse(null);

        if(target != null){ //DB에 새로 생성하는 것이 아닌 수정하는 것이기 때문에 비어있는지 안비어있는지 확인해야함.
            cacArticleRepository.save(cacArticleEntity);
        }

        return "redirect:/article/" + cacArticleEntity.getId();
    }


}