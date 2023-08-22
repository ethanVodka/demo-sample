package com.example_ethan.demosample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// RESTコントローラーとして動作するクラス
@RestController
public class PersonController {
    // PersonDaoのインジェクション
    @Autowired
    PersonDao personDao;

    // 全てのPersonエンティティを取得するエンドポイント
    @GetMapping("/persons")
    public List<Person> getAll() {
        return personDao.selectAll();
    }

    // 新しいPersonエンティティを作成するエンドポイント
    @PostMapping("/person")
    public Person create(@RequestBody Person person) {
        personDao.insert(person);
        return person;
    }

    // 指定されたIDのPersonエンティティを削除するエンドポイント
    @DeleteMapping("/person/{id}")
    public String delete(@PathVariable Integer id) {
        Person person = new Person();
        person.id = id;
        int deletedRows = personDao.delete(person);

        // 削除が成功したかどうかのメッセージを返す
        return deletedRows > 0 ? "Deleted successfully" : "Deletion failed";
    }
}
