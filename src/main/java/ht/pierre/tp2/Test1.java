package ht.pierre.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test1 {

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

        // Question simple
        String response1 = model.chat("Bonjour !");
        System.out.println("Q1: Bonjour !");
        System.out.println("R1: " + response1);

        // Heure
        String response2 = model.chat("Quelle heure est-il ?");
        System.out.println("\nQ2: Quelle heure est-il ?");
        System.out.println("R2: " + response2);

        // Test mémoire
        String response3 = model.chat("Bonjour, je m'appelle Pierre.");
        System.out.println("\nQ3: Bonjour, je m'appelle Pierre.");
        System.out.println("R3: " + response3);

        String response4 = model.chat("Quel est mon nom ?");
        System.out.println("\nQ4: Quel est mon nom ?");
        System.out.println("R4: " + response4);
    }
}