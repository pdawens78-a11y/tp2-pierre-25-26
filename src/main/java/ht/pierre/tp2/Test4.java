package ht.pierre.tp2;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel.TaskType;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4 {

    public static void main(String[] args) {

        // clé API
        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("Clé GEMINI_KEY manquante !");
        }

        // modèle
        EmbeddingModel model = GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-embedding-001")
                .taskType(TaskType.SEMANTIC_SIMILARITY)
                .outputDimensionality(300)
                .timeout(Duration.ofSeconds(2))
                .build();

        // Phrases à comparer
        String phrase1 = "Le chat dort sur le canapé";
        String phrase2 = "Le chien dort sur le canapé";
        String phrase3 = "Je fais mes devoirs de Java";

        // Embeddings
        Embedding embedding1 = model.embed(phrase1).content();
        Embedding embedding2 = model.embed(phrase2).content();
        Embedding embedding3 = model.embed(phrase3).content();

        // Similarités
        double sim12 = CosineSimilarity.between(embedding1, embedding2);
        double sim13 = CosineSimilarity.between(embedding1, embedding3);

        // Affichage
        System.out.println("Phrase 1: " + phrase1);
        System.out.println("Phrase 2: " + phrase2);
        System.out.println("Phrase 3: " + phrase3);

        System.out.println("\nSimilarité (1 vs 2): " + sim12);
        System.out.println("Similarité (1 vs 3): " + sim13);
    }
}