
package com.example.TomEEServlets;

import com.github.badoualy.telegram.api.Kotlogram;
import com.github.badoualy.telegram.api.TelegramApp;
import com.github.badoualy.telegram.api.TelegramClient;
import com.github.badoualy.telegram.tl.api.TLUser;
import com.github.badoualy.telegram.tl.api.auth.TLAuthorization;
import com.github.badoualy.telegram.tl.api.auth.TLSentCode;
import com.github.badoualy.telegram.tl.api.contacts.TLContacts;
import com.github.badoualy.telegram.tl.exception.RpcErrorException;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named("startApplicationTelegram") // эта аннотация преврашает класс в cdi бин и делает его видимым для jsf страницы
@SessionScoped
public class StartApplicationTelegram  implements Serializable{
    
    private String kodTelegram;
    private TelegramApp application;
    private boolean accessed; //когда получаем доступ к телеграмму устанавливаем в истину
    private TelegramClient client;
    private TLSentCode sentCode;
    private String phohe_number;
    private String resultOperationTelegram;
    private ApiStorage state; 
    
    public StartApplicationTelegram() {
        this.application = new TelegramApp(868145, "f060da0313295164b197cea7de9f5e53", "Model",  "SysVer", "1", "en");
        this.resultOperationTelegram = "input phohe_number";
        this.state = new ApiStorage();
        this.client = Kotlogram.getDefaultClient(application, state);
        this.resultOperationTelegram = state.AUTH_KEY_FILE.toString();
         
    }

    public TelegramApp getApplication() {
        return application;
    }

    public String getResultOperationTelegram() {
        return resultOperationTelegram;
    }

    public TelegramClient getClient() {
        return client;
    }

    public TLSentCode getSentCode() {
        return sentCode;
    }

    public boolean isAccessed() {
        return accessed;
    }

    public String getPhohe_number() {
        return phohe_number;
    }

    public void setPhohe_number(String phohe_number) {
        this.phohe_number = phohe_number;
    }    
    
    public String getKodTelegram() {
        return kodTelegram;
    }

    public void setKodTelegram(String kodTelegram) {
        this.kodTelegram = kodTelegram;
    }
   
    public String goToDataTelegram() {
        if ("".equals(kodTelegram)) {
           return "no value code"; 
        } else{
            try{
                resultOperationTelegram = kodTelegram.trim();
                TLAuthorization authorization = client.authSignIn(phohe_number, sentCode.getPhoneCodeHash(), kodTelegram.trim());
                TLUser self = authorization.getUser().getAsUser();
                resultOperationTelegram = self.getUsername();
               // TLContacts tContacts = (TLContacts) client.contactsGetContacts("");
               // resultOperationTelegram  = self.getUsername() + " кол " + tContacts.getUsers().size();
            } catch (RpcErrorException | IOException e){
                resultOperationTelegram = e.getMessage();
                e.printStackTrace();
            }
            
            return "no value code"; 
           //return "success";
        }
    }
    
    public void connectTelegram() { 
       if ("".equals(phohe_number) == false) {
           try{
              sentCode = client.authSendCode(false, phohe_number, true);
           } catch (RpcErrorException | IOException e) {
              resultOperationTelegram = e.getMessage();
              e.printStackTrace();
           } finally {
              client.close(); // Important, do not forget this, or your process won't finish
           }
       }        
    }  
    
    public void changedkodTelegram(ValueChangeEvent e) { 
       //процедура вызывается при изменении поля которое связано с бином в jsf странице но
       //вызывается она только когда срабатывает action формы.
       System.out.println(e.getNewValue().toString()); 
    }  
    
}
