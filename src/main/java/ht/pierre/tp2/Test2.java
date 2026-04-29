package ht.pierre.tp2;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test2 {

    public static void main(String[] args) {

        // =========================
        // Clé API
        // =========================
        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("Clé GEMINI_KEY manquante !");
        }

        // =========================
        // Modèle
        // =========================
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        // =========================
        // Question (même que Test1)
        // =========================
        String question = "Bonjour, comment ça va ?";

        // =========================
        // Appel avec ChatResponse
        // =========================
        ChatResponse response = model.chat(UserMessage.from(question));

        // =========================
        // Réponse texte
        // =========================
        String answer = response.aiMessage().text();

        // =========================
        // Tokens
        // =========================
        int inputTokens = response.tokenUsage().inputTokenCount();
        int outputTokens = response.tokenUsage().outputTokenCount();
        int totalTokens = response.tokenUsage().totalTokenCount();

        // =========================
        // Affichage
        // =========================
        System.out.println("Question: " + question);
        System.out.println("Réponse: " + answer);

        System.out.println("\nTokens entrée: " + inputTokens);
        System.out.println("Tokens sortie: " + outputTokens);
        System.out.println("Tokens total: " + totalTokens);

        // =========================
        // Calcul du coût
        // =========================

        // Prix (d'après ton tableau)
        double inputPricePerMillion = 0.30;
        double outputPricePerMillion = 2.50;

        double inputCost = (inputTokens / 1_000_000.0) * inputPricePerMillion;
        double outputCost = (outputTokens / 1_000_000.0) * outputPricePerMillion;

        double totalCost = inputCost + outputCost;

        System.out.println("\nCoût entrée ($): " + inputCost);
        System.out.println("Coût sortie ($): " + outputCost);
        System.out.println("Coût total ($): " + totalCost);

        // =========================
        // Nombre de requêtes pour 1$
        // =========================
        double requestsForOneDollar = 1 / totalCost;

        System.out.println("Nombre de requêtes pour 1$: " + (int) requestsForOneDollar);
    }
}