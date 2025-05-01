INSERT INTO authority (role) VALUES
    ('USER');

-- Insert 5 users
INSERT INTO users (email, password, name, authority_id) VALUES
                                                            ('user1@example.com', 'pass1', 'User One', (select id from AUTHORITY where role ilike 'USER')),
                                                            ('user2@example.com', 'pass2', 'User Two', (select id from AUTHORITY where role ilike 'USER')),
                                                            ('user3@example.com', 'pass3', 'User Three', (select id from AUTHORITY where role ilike 'USER')),
                                                            ('user4@example.com', 'pass4', 'User Four', (select id from AUTHORITY where role ilike 'USER')),
                                                            ('user5@example.com', 'pass5', 'User Five', (select id from AUTHORITY where role ilike 'USER'));

-- Insert 10 restaurants
INSERT INTO restaurant (name) VALUES
                                  ('Pizza Palace'),
                                  ('Burger Barn'),
                                  ('Sushi Central'),
                                  ('Taco Town'),
                                  ('Pasta Place'),
                                  ('Grill House'),
                                  ('Curry Corner'),
                                  ('Vegan Vibes'),
                                  ('BBQ Joint'),
                                  ('Noodle Nest');

-- Insert 10 dishes for each of the 10 restaurants (100 dishes total)
-- Assuming auto-increment ID
INSERT INTO dish (name, restaurant_id, price) VALUES
-- Restaurant 1
('Dish 1-1', 1, 10), ('Dish 1-2', 1, 11), ('Dish 1-3', 1, 12), ('Dish 1-4', 1, 13), ('Dish 1-5', 1, 14),
('Dish 1-6', 1, 15), ('Dish 1-7', 1, 16), ('Dish 1-8', 1, 17), ('Dish 1-9', 1, 18), ('Dish 1-10', 1, 19),

-- Restaurant 2
('Dish 2-1', 2, 10), ('Dish 2-2', 2, 11), ('Dish 2-3', 2, 12), ('Dish 2-4', 2, 13), ('Dish 2-5', 2, 14),
('Dish 2-6', 2, 15), ('Dish 2-7', 2, 16), ('Dish 2-8', 2, 17), ('Dish 2-9', 2, 18), ('Dish 2-10', 2, 19),

-- Restaurant 3
('Dish 3-1', 3, 10), ('Dish 3-2', 3, 11), ('Dish 3-3', 3, 12), ('Dish 3-4', 3, 13), ('Dish 3-5', 3, 14),
('Dish 3-6', 3, 15), ('Dish 3-7', 3, 16), ('Dish 3-8', 3, 17), ('Dish 3-9', 3, 18), ('Dish 3-10', 3, 19),

-- Restaurant 4
('Dish 4-1', 4, 10), ('Dish 4-2', 4, 11), ('Dish 4-3', 4, 12), ('Dish 4-4', 4, 13), ('Dish 4-5', 4, 14),
('Dish 4-6', 4, 15), ('Dish 4-7', 4, 16), ('Dish 4-8', 4, 17), ('Dish 4-9', 4, 18), ('Dish 4-10', 4, 19),

-- Restaurant 5
('Dish 5-1', 5, 10), ('Dish 5-2', 5, 11), ('Dish 5-3', 5, 12), ('Dish 5-4', 5, 13), ('Dish 5-5', 5, 14),
('Dish 5-6', 5, 15), ('Dish 5-7', 5, 16), ('Dish 5-8', 5, 17), ('Dish 5-9', 5, 18), ('Dish 5-10', 5, 19),

-- Restaurant 6
('Dish 6-1', 6, 10), ('Dish 6-2', 6, 11), ('Dish 6-3', 6, 12), ('Dish 6-4', 6, 13), ('Dish 6-5', 6, 14),
('Dish 6-6', 6, 15), ('Dish 6-7', 6, 16), ('Dish 6-8', 6, 17), ('Dish 6-9', 6, 18), ('Dish 6-10', 6, 19),

-- Restaurant 7
('Dish 7-1', 7, 10), ('Dish 7-2', 7, 11), ('Dish 7-3', 7, 12), ('Dish 7-4', 7, 13), ('Dish 7-5', 7, 14),
('Dish 7-6', 7, 15), ('Dish 7-7', 7, 16), ('Dish 7-8', 7, 17), ('Dish 7-9', 7, 18), ('Dish 7-10', 7, 19),

-- Restaurant 8
('Dish 8-1', 8, 10), ('Dish 8-2', 8, 11), ('Dish 8-3', 8, 12), ('Dish 8-4', 8, 13), ('Dish 8-5', 8, 14),
('Dish 8-6', 8, 15), ('Dish 8-7', 8, 16), ('Dish 8-8', 8, 17), ('Dish 8-9', 8, 18), ('Dish 8-10', 8, 19),

-- Restaurant 9
('Dish 9-1', 9, 10), ('Dish 9-2', 9, 11), ('Dish 9-3', 9, 12), ('Dish 9-4', 9, 13), ('Dish 9-5', 9, 14),
('Dish 9-6', 9, 15), ('Dish 9-7', 9, 16), ('Dish 9-8', 9, 17), ('Dish 9-9', 9, 18), ('Dish 9-10', 9, 19),

-- Restaurant 10
('Dish 10-1', 10, 10), ('Dish 10-2', 10, 11), ('Dish 10-3', 10, 12), ('Dish 10-4', 10, 13), ('Dish 10-5', 10, 14),
('Dish 10-6', 10, 15), ('Dish 10-7', 10, 16), ('Dish 10-8', 10, 17), ('Dish 10-9', 10, 18), ('Dish 10-10', 10, 19);
