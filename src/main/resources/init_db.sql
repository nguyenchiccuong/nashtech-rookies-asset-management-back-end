CREATE DATABASE shop-service;

INSERT INTO roles(id , name) VALUES(1001, 'ROLE_USER');
INSERT INTO roles(id , name) VALUES(1002 , 'ROLE_SUPER_ADMINISTRATOR');
INSERT INTO roles(id , name) VALUES(1003 , 'ROLE_ADMINISTRATOR');

INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(18, '2021-07-26 21:19:21.108', 'Laptop - thiết bị IT', false, 'Laptop - thiết bị IT', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(19, '2021-07-26 21:19:39.729', 'Máy ảnh quay phim', false, 'Máy ảnh quay phim', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(20, '2021-07-26 21:20:09.134', 'Điện gia dụng', false, 'Điện gia dụng', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(21, '2021-07-26 21:20:27.643', 'Nhà cửa đời sống', false, 'Nhà cửa đời sống', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(22, '2021-07-26 21:21:04.070', 'Hàng tiêu dùng - thực phẩm', false, 'Hàng tiêu dùng - thực phẩm', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(23, '2021-07-26 21:21:21.130', 'Làm đẹp - sức khỏe', false, 'Làm đẹp - sức khỏe', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(24, '2021-07-26 21:21:41.508', 'Thời trang - phụ kiện', false, 'Thời trang - phụ kiện', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(25, '2021-07-26 21:22:08.272', 'Xe máy - ôtô - xe đạp', false, 'Xe máy - ôtô - xe đạp', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(26, '2021-07-26 21:22:40.248', 'Sách - vpp - quà tặng', false, 'Sách - vpp - quà tặng', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(8, '2021-07-26 20:49:55.715', 'Điện tử - điện lạnh', false, 'Điện tử - điện lạnh', NULL, NULL, NULL);
INSERT INTO public.categories
(id, created_date, description, is_deleted, "name", updated_date, image_id, parent_id)
VALUES(9, '2021-07-26 20:50:04.874', 'Điện thoại - máy tính bảng', false, 'Điện thoại - máy tính bảng', NULL, NULL, NULL);

INSERT INTO public.address
(id, city, country, created_date, district, is_deleted, street_address, updated_date, ward)
VALUES(3, 'ho chi minh', 'viet nam', NULL, '1', false, 'Tran hung dao', NULL, 'pham ngu lao');
INSERT INTO public.address
(id, city, country, created_date, district, is_deleted, street_address, updated_date, ward)
VALUES(5, 'ho chi minh', 'viet nam', NULL, 'tan binh', false, 'cong Hoa', NULL, '2');
INSERT INTO public.address
(id, city, country, created_date, district, is_deleted, street_address, updated_date, ward)
VALUES(7, 'ho chi minh', 'viet nam', NULL, 'tan binh', false, 'cong Hoa', NULL, '2');


INSERT INTO public.organizations
(id, created_date, "name", address_id, image_id)
VALUES(2, NULL, 'Samsung', 3, NULL);
INSERT INTO public.organizations
(id, created_date, "name", address_id, image_id)
VALUES(4, NULL, 'Apple', 5, NULL);
INSERT INTO public.organizations
(id, created_date, "name", address_id, image_id)
VALUES(6, NULL, 'Sony', 7, NULL);


INSERT INTO public.brands
(id, is_deleted, "name", updated_date, organization_id)
VALUES(29, false, 'Apple', NULL, 4);
INSERT INTO public.brands
(id, is_deleted, "name", updated_date, organization_id)
VALUES(30, false, 'Samsung', NULL, 4);


INSERT INTO public.brands_categories
(brand_id, category_id)
VALUES(29, 8);
INSERT INTO public.brands_categories
(brand_id, category_id)
VALUES(29, 9);
INSERT INTO public.brands_categories
(brand_id, category_id)
VALUES(30, 9);
INSERT INTO public.brands_categories
(brand_id, category_id)
VALUES(30, 8);

