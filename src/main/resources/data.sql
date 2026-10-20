USE projekt2_gruppe_5_db;

INSERT INTO users (name, email, password) VALUES
    ('Test Bruger', 'test@test.dk', '1234');

INSERT INTO wishlist (title, user_id) VALUES
    ('Jul 2026', 1);

INSERT INTO wish (name, description, price, link, wishlist_id) VALUES
                                                                   ('Hovedtelefoner', 'Trådløse, gerne sorte', 899.00, 'https://example.com', 1),
                                                                   ('Bog', 'Noget om programmering', 249.00, NULL, 1);