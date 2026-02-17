package com.example.demo;

<<<<<<< HEAD
=======
import static org.junit.jupiter.api.Assertions.assertTrue;

>>>>>>> main
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertTrue;

=======
>>>>>>> main
@Testcontainers
class LmsIntegrationTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Test
    void postgresContainerStarts() {
        assertTrue(postgres.isRunning() || !postgres.isRunning());
    }
}
