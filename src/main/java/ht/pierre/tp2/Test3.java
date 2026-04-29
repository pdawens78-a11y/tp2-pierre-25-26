package ht.pierre.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3 {

    public static void main(String[] args) {

        // clé API
        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("Clé GEMINI_KEY manquante !");
        }

        // modèle
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        // Template de traduction
        PromptTemplate template = PromptTemplate.from(
                "Traduis le texte suivant en anglais : {{texte}}"
        );

        // Texte à traduire
        String texte = "Bonjour, comment ça va ?";

        // Création du prompt
        Prompt prompt = template.apply(Map.of("texte", texte));

        // Appel au modèle
        String response = model.chat(prompt.text());

        // Affichage
        System.out.println("Texte original: " + texte);
        System.out.println("Traduction: " + response);
    }
}