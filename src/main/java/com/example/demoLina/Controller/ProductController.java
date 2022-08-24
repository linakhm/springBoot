package com.example.demoLina.Controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoLina.Model.Product;
import com.example.demoLina.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demoLina.Repository.ProductRepo;
import org.springframework.ui.Model;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demoLina.Model.Login;
import com.example.demoLina.Service.LoginService;



@Controller
public class ProductController {
    @Autowired
    private LoginService userService;
@Autowired
    private ProductService ps;

    @Autowired
    private ProductRepo productRepo;
  /*  @RequestMapping("/")
//    @ResponseBody
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");
        return modelAndView;
    }
*/
  @RequestMapping("/")
 /* public String viewHomePage(Model model) {
      Page<Product> page= ps.listAll();
      List<Product> listProducts=page.getContent();
      model.addAttribute("products", listProducts);
      return "products";
  }*/
  public String viewHomePage(Model model) {
      return findPaginated(1, "Name", "asc", model);
  }
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "newproduct";
    }




      /*  public String viewHomePage(Product model) {
            List<Product> listp = ps.listAll();
            model.addAttribute("liststudent", listp);
            System.out.print("Get / ");
            return "index";
    }*/

  /*  @GetMapping("/products")
    public List<Product> findAll() {
        return this.productRepo.findAll();
    }
*/
  @GetMapping("/page/{pageNo}")
  public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                              @RequestParam("sortField") String sortField,
                              @RequestParam("sortDir") String sortDir,
                              Model model) {
      int pageSize = 5;

      Page<Product> page = ps.findPaginated(pageNo, pageSize, sortField, sortDir);
      List<Product> products = page.getContent();

      model.addAttribute("currentPage", pageNo);
      model.addAttribute("totalPages", page.getTotalPages());
      model.addAttribute("totalItems", page.getTotalElements());

      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDir", sortDir);
      model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

      model.addAttribute("products", products);
      return "products";
  }


    @PostMapping(value = "/save")
    public String saveProduct(@RequestBody Product p) {
        productRepo.save(p);
        return "saved";
    }

    @PostMapping("/saveProduct")
    public String saveProd(@ModelAttribute("product") Product product) {
        // save product to database
        ps.saveProduct(product);
        return "redirect:/";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id) {

        // call delete employee method
        this.ps.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        // get employee from the service
        Product product = ps.getProductById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        return "update_prod";
    }


    @PostMapping(value = "/validerprod")
    public String validerProduit( @RequestBody List <Product> listp) {
        productRepo.saveAll(listp);
        return listp.size()+ "produits validés ";
    }

    @GetMapping("/products/{id}/")
    public Product findById(@PathVariable int id) throws Exception {
        return this.productRepo.findById(id).orElseThrow(() -> new Exception("Product n'existe pas"));
    }

    @PutMapping(value = "update/{id}")

    public String updateProd(@PathVariable int id, @RequestBody Product p) {
        Product updatedProd = productRepo.findById(id).get();
        updatedProd.setName(p.getName());
        updatedProd.setQuantity(p.getQuantity());
        updatedProd.setPrice(p.getPrice());
        productRepo.save(updatedProd);
        return "updated successfully";
    }
/* delete in postman
    @DeleteMapping(value = "/delete/{id}")
    public String deleteProd(@PathVariable int id) {
        Product deleteProd = productRepo.findById(id).get();

        productRepo.delete(deleteProd);
return "deleted successfully ";
    }

*/

    @GetMapping("/login")

    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user ) {

        Login oauthUser = userService.login(user.getUsername(), user.getPassword());


        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser))
        {

            return "redirect:/";


        } else {
            return "redirect:/login";


        }

    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {


        return "redirect:/login";
    }

}