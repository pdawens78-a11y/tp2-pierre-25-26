package ht.pierre.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import ht.pierre.tp2.outils.meteo.MeteoTool;

import java.util.Scanner;

public class Test7 {

    // Interface assistant
    interface AssistantMeteo {
        String chat(String userMessage);
    }

    public static void main(String[] args) {

        String llmKey = System.getenv("GEMINI_KEY");
        if (llmKey == null) {
            System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie.");
            return;
        }

        // Modèle
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-2.5-flash-lite")
                .temperature(0.3)
                .build();

        // Création de l'assistant avec outil météo
        AssistantMeteo assistant =
                AiServices.builder(AssistantMeteo.class)
                        .chatModel(model)
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(20)) // L'utilisation des outils génère des messages
                        .tools(new MeteoTool())  // Ajout de l'outil
                        .build();

        // Lancer la conversation
        conversationAvec(assistant);
    }

    // Méthode pour poser questions a l'assistant Meteo
    private static void conversationAvec(AssistantMeteo assistant) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("==================================================");
                System.out.println("Posez votre question : ");
                String question = scanner.nextLine();

                if (question.isBlank()) {
                    continue;
                }

                if ("fin".equalsIgnoreCase(question)) {
                    break;
                }

                System.out.println("==================================================");

                String reponse = assistant.chat(question);
                System.out.println("Assistant : " + reponse);
                System.out.println("==================================================");
            }
        }
    }
}