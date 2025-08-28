package br.com.prover;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class WordCounterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String phrase;
	private Map<String, Long> wordCount = new HashMap<>();
	
	public void analyze() {
	    if (phrase == null || phrase.trim().isEmpty()) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_WARN,
	                             "Aviso",
	                             "Digite uma frase antes de analisar."));
	        return;
	    }
	    
        wordCount = Arrays.stream(phrase.toLowerCase()
                .replaceAll("[^a-zà-ú0-9 ]", "")
                .split("\\s+"))
        		.collect(Collectors.groupingBy(w -> w, HashMap::new, Collectors.counting()));
       
	}

    public int getDistinctWordCount() {
        return wordCount.size();
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Map<String, Long> getWordCount() {
        return wordCount;
    }
    
}
