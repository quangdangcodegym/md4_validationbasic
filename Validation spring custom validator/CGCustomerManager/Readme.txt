Spring validation use hibernate
https://www.baeldung.com/spring-thymeleaf-error-messages
use custom messages resource
https://www.baeldung.com/spring-custom-validation-message-source


// Khi dùng VALIDATE BEAN với anotation @NotNull, @Size của package  javax.validation.constraints.
Đây chính là spring hibernate validation

// Vấn đề là khi custom Validator thì spring sẽ CHỈ sử dụng validator mới custom và ẨN tất cả các VALIDATE BEAN
- Neu dung EmailValidator, ta để @Component trên class thì Spring container đã tạo 1 bean là emailValidator. Bây giờ
chỉ cần gắn vào funtion binder
@InitBinder
    public void initBinder(WebDataBinder binder){
        //binder.setValidator(emailValidator);
        binder.setValidator(webAppValidator);
    }
- Neu tao 1 class WebAppValidator de chua nhieu validator thi ...
can vao config tạo 1 bean bang phuong thuc get
@Bean
public WebAppValidator webAppValidator(){
    Set<Validator> springValidators = new HashSet<>();
    springValidators.add(new EmailValidator());
    springValidators.add(new LastNameValidator());

    WebAppValidator v =  new WebAppValidator();
    v.setSpringvalidator(springValidators);

    return  v;
}

