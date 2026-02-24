-- Insert initial portfolio data
-- Customer 1: John Doe (3 stocks)
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (1, 'John Doe', 'AAPL', 50);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (1, 'John Doe', 'GOOGL', 30);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (1, 'John Doe', 'MSFT', 40);

-- Customer 2: Jane Smith (3 stocks)
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (2, 'Jane Smith', 'TSLA', 25);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (2, 'Jane Smith', 'AMZN', 15);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (2, 'Jane Smith', 'NVDA', 45);

-- Customer 3: Bob Johnson (3 stocks)
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (3, 'Bob Johnson', 'META', 35);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (3, 'Bob Johnson', 'NFLX', 10);
INSERT INTO portfolio (customer_id, customer_name, stock_symbol, quantity) VALUES (3, 'Bob Johnson', 'AMD', 60);



