/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daocat;

import java.util.List;
import model.Cats;
/**
 *
 * @author LENOVO
 */
public interface dao {
    void createCats(Cats cats);
    List<Cats> readCats();
    void updateCats(Cats cats);
    void deleteCats(String name);
}
