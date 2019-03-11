USE j2ee_hw;
INSERT INTO canteens (id, name, password, district, address, shipping_fee, packaging_fee, type, balance) VALUES ('canteen', '测试餐厅', 'password', 'GULOU', '中山路221号', 5, 3, 'SNACK', 0);

INSERT INTO offers (id, base, description, discount, end, start, canteen_id) VALUES ('1', '50', '满50减10', '10', '2019-05-31', '2019-01-01', 'canteen');

INSERT INTO clients (name, password, balance, mail, ranking, grade, state) VALUES ('client1', 'password', '1000', '2544194336@qq.com', '0', '9', 'ACTIVE');
INSERT INTO clients (name, password, balance, mail, ranking, grade, state) VALUES ('client2', 'password', '20', '161250173@smail.nju.edu.cn', '0', '9', 'ABANDON');

INSERT INTO goods (id, name, goods_type, canteen_id, quantity, price, description, state) VALUES ('Ccanteen0', 'goods1', 'staplefood', 'canteen', '-1', '20.0', 'the first commodities in canteen', 'INSTOCK');
INSERT INTO commodities (id, start, end) VALUES ('Ccanteen0', '2018-12-31', '2019-01-01');
INSERT INTO goods (id, name, goods_type, canteen_id, quantity, price, description, state) VALUES ('Ccanteen1', 'goods2', 'dessert', 'canteen', '-1', '20.0', 'the second commodities in canteen', 'INSTOCK');
INSERT INTO commodities (id, start, end) VALUES ('Ccanteen1', '2018-12-31', '2019-01-01');

INSERT INTO goods (id, name, goods_type, canteen_id, quantity, price, state, description) VALUES ('Scanteen0', 'tao_can_1', 'setmeal', 'canteen', '-1', '35.0', 'INSTOCK', '第一种套餐');
INSERT INTO set_meals (id, end, start) VALUES ('Scanteen0', '2019-12-31', '2019-01-01');

INSERT INTO set_meal_info (id, commodity_id, set_meals_id, quantity) VALUES ('1', 'Ccanteen0', 'Scanteen0', '1');
INSERT INTO set_meal_info (id, commodity_id, set_meals_id, quantity) VALUES ('2', 'Ccanteen1', 'Scanteen0', '1');

INSERT INTO orders (id, order_time, state, canteen_id, client_id, district, address_detail, cancel_fee, final_money, initial_price) VALUES ('2019-03-03T09:37:48.855|9', '2019-03-03T09:37:48', 'FINISHED', 'canteen', 'client1', 'GULOU', '南京大学鼓楼区陶园南楼202', '0', '100', '100');
INSERT INTO orders (id, order_time, state, canteen_id, client_id, district, address_detail, cancel_fee, final_money, initial_price) VALUES ('2019-03-03T09:37:48.855|11', '2019-03-03T09:37:48', 'CANCELED', 'canteen', 'client1', 'GULOU', '南京大学鼓楼区陶园南楼202', '10', '100', '100');

INSERT INTO order_goods_info (id, goods_id, orders_id, quantity) VALUES (1, 'Ccanteen0', '2019-02-19T09:37:48.855|9', '1');
INSERT INTO order_goods_info (id, goods_id, orders_id, quantity) VALUES (2, 'Ccanteen1', '2019-02-19T09:37:48.855|9', '1');
INSERT INTO order_goods_info (id, goods_id, orders_id, quantity) VALUES (3, 'Ccanteen1', '2019-02-19T09:37:48.855|11', '1');

INSERT INTO address (id, client_name, district, detail, state) VALUES (1, 'client1', 'GULOU', '南京大学鼓楼校区陶园南楼', 'DEFAULT');

INSERT INTO modify_canteen_info (id, name, district, address_detail, type, state, comment, canteen_id) VALUES (1, '测试餐厅', 'GULOU', '中山路221号', 'FAST_FOOD_RESTAURANT', 'WAITING', NULL, 'canteen');

INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2018-08', 1);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2018-09', 2);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2018-10', 3);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2018-11', 4);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2018-12', 5);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2019-01', 10);
INSERT INTO month_canteen_statistics (id, canteen_num) VALUES ('2019-02', 11);

INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2018-08', 1, 1, 0, 0, 0, 0, 0);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2018-09', 2, 1, 1, 0, 0, 0, 0);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2018-10', 3, 1, 2, 0, 0, 0, 0);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2018-11', 4, 1, 1, 2, 0, 0, 0);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2018-12', 5, 1, 1, 2, 1, 0, 0);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2019-01', 10, 1, 1, 1, 1, 5, 1);
INSERT INTO month_client_statistics (id, client_num, client0num, client1num, client2num, client3num, client4num, client5num) VALUES ('2019-02', 11, 1, 1, 1, 1, 5, 2);

INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2018-08', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2018-09', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2018-10', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2018-11', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2018-12', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2019-01', 100, 70, 30, 1, 0, 1);
INSERT INTO month_finance_statistics (id, total_sum, canteen_sum, plat_sum, order_sum, cancel_sum, finished_sum) VALUES ('2019-02', 100, 70, 30, 1, 0, 1);
