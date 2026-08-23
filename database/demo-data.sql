-- Synthetic demo dataset for local portfolio demonstrations.
-- Names and financial values below are fictional and do not represent real customers.

INSERT INTO customers (name, email, segment, status) VALUES
('Northstar Retail Group', 'finance@northstar-demo.example', 'Enterprise', 'Active'),
('Greenfield Health Systems', 'ops@greenfield-demo.example', 'Enterprise', 'Active'),
('Cedar Labs', 'admin@cedarlabs-demo.example', 'Growth', 'Active'),
('BluePeak Logistics', 'finance@bluepeak-demo.example', 'Growth', 'Active'),
('Summit Digital Studio', 'hello@summit-demo.example', 'Small Business', 'Prospect')
ON CONFLICT (email) DO NOTHING;

INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '150 days', 'Subscription', 78000.00, 'REVENUE' FROM customers WHERE email='finance@northstar-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '120 days', 'Implementation', 94000.00, 'REVENUE' FROM customers WHERE email='ops@greenfield-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '90 days', 'Cloud Services', 102000.00, 'REVENUE' FROM customers WHERE email='admin@cedarlabs-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '60 days', 'Managed Services', 118000.00, 'REVENUE' FROM customers WHERE email='finance@bluepeak-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '30 days', 'Subscription', 131000.00, 'REVENUE' FROM customers WHERE email='finance@northstar-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE, 'Platform Services', 145000.00, 'REVENUE' FROM customers WHERE email='ops@greenfield-demo.example';

INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '120 days', 'Infrastructure', 28000.00, 'EXPENSE' FROM customers WHERE email='finance@northstar-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '90 days', 'Operations', 34000.00, 'EXPENSE' FROM customers WHERE email='ops@greenfield-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '60 days', 'Support', 39000.00, 'EXPENSE' FROM customers WHERE email='admin@cedarlabs-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE - INTERVAL '30 days', 'Infrastructure', 42000.00, 'EXPENSE' FROM customers WHERE email='finance@bluepeak-demo.example';
INSERT INTO financial_transactions (customer_id, transaction_date, category, amount, transaction_type)
SELECT id, CURRENT_DATE, 'Operations', 47000.00, 'EXPENSE' FROM customers WHERE email='finance@northstar-demo.example';
