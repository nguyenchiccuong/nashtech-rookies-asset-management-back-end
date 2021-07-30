-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
                                id int8 NOT NULL,
                                city varchar(255) NOT NULL,
                                country varchar(255) NOT NULL,
                                created_date timestamp NULL,
                                district varchar(255) NOT NULL,
                                is_deleted bool NULL,
                                street_address varchar(255) NOT NULL,
                                updated_date timestamp NULL,
                                ward varchar(255) NOT NULL,
                                CONSTRAINT address_pkey PRIMARY KEY (id)
);
CREATE INDEX address_id_index ON public.address USING btree (id);


-- public.images definition

-- Drop table

-- DROP TABLE public.images;

CREATE TABLE public.images (
                               id int8 NOT NULL,
                               created_date timestamp NULL,
                               is_deleted bool NULL,
                               "name" varchar(255) NULL,
                               updated_date timestamp NULL,
                               url varchar(1024) NULL,
                               CONSTRAINT images_pkey PRIMARY KEY (id)
);
CREATE INDEX image_idx ON public.images USING btree (id, name);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
                              id int8 NOT NULL,
                              "name" varchar(50) NULL,
                              CONSTRAINT roles_pkey PRIMARY KEY (id)
);
CREATE INDEX role_idx ON public.roles USING btree (id, name);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id int8 NOT NULL,
                              created_date timestamp NULL,
                              email varchar(50) NULL,
                              is_deleted bool NULL,
                              "password" varchar(120) NULL,
                              phone varchar(15) NULL,
                              status varchar(255) NULL,
                              updated_date timestamp NULL,
                              username varchar(20) NULL,
                              CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
                              CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
                              CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE INDEX idindex ON public.users USING btree (id, username, email);


-- public.categories definition

-- Drop table

-- DROP TABLE public.categories;

CREATE TABLE public.categories (
                                   id int8 NOT NULL,
                                   created_date timestamp NULL,
                                   description varchar(255) NULL,
                                   is_deleted bool NULL,
                                   "name" varchar(255) NOT NULL,
                                   updated_date timestamp NULL,
                                   image_id int8 NULL,
                                   parent_id int8 NULL,
                                   CONSTRAINT categories_pkey PRIMARY KEY (id),
                                   CONSTRAINT uk_t8o6pivur7nn124jehx7cygw5 UNIQUE (name),
                                   CONSTRAINT fkqhmw54g2p4xu0k71mblvlqfvi FOREIGN KEY (image_id) REFERENCES public.images(id),
                                   CONSTRAINT fksaok720gsu4u2wrgbk10b5n8d FOREIGN KEY (parent_id) REFERENCES public.categories(id)
);
CREATE INDEX category_idx ON public.categories USING btree (id, name);


-- public.organizations definition

-- Drop table

-- DROP TABLE public.organizations;

CREATE TABLE public.organizations (
                                      id int8 NOT NULL,
                                      created_date timestamp NULL,
                                      "name" varchar(255) NOT NULL,
                                      address_id int8 NULL,
                                      image_id int8 NULL,
                                      CONSTRAINT organizations_pkey PRIMARY KEY (id),
                                      CONSTRAINT uk_p9pbw3flq9hkay8hdx3ypsldy UNIQUE (name),
                                      CONSTRAINT fk5y0314crw0r1vn4awyvs4n67j FOREIGN KEY (address_id) REFERENCES public.address(id),
                                      CONSTRAINT fkbc6dcbaj5wy2hemm7p7hmq0wf FOREIGN KEY (image_id) REFERENCES public.images(id)
);
CREATE INDEX organization_idx ON public.organizations USING btree (id, name);


-- public.user_roles definition

-- Drop table

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles (
                                   user_id int8 NOT NULL,
                                   role_id int8 NOT NULL,
                                   CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
                                   CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id),
                                   CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.users_addresses definition

-- Drop table

-- DROP TABLE public.users_addresses;

CREATE TABLE public.users_addresses (
                                        user_id int8 NOT NULL,
                                        addresses_id int8 NOT NULL,
                                        CONSTRAINT uk_fkg2t84ux3d2l2pg8atpsbljx UNIQUE (addresses_id),
                                        CONSTRAINT fke4noxx3lwcqyb2puartxil7g1 FOREIGN KEY (addresses_id) REFERENCES public.address(id),
                                        CONSTRAINT fkrpoauh74gtrrvj9m8skx6vti1 FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.brands definition

-- Drop table

-- DROP TABLE public.brands;

CREATE TABLE public.brands (
                               id int8 NOT NULL,
                               is_deleted bool NULL,
                               "name" varchar(255) NOT NULL,
                               updated_date timestamp NULL,
                               organization_id int8 NULL,
                               CONSTRAINT brands_pkey PRIMARY KEY (id),
                               CONSTRAINT uk_oce3937d2f4mpfqrycbr0l93m UNIQUE (name),
                               CONSTRAINT fkq646smhet4r1djxbbad0hjwjq FOREIGN KEY (organization_id) REFERENCES public.organizations(id)
);
CREATE INDEX brand_name_index ON public.brands USING btree (name);


-- public.brands_categories definition

-- Drop table

-- DROP TABLE public.brands_categories;

CREATE TABLE public.brands_categories (
                                          brand_id int8 NOT NULL,
                                          category_id int8 NOT NULL,
                                          CONSTRAINT brands_categories_pkey PRIMARY KEY (brand_id, category_id),
                                          CONSTRAINT fk58ksmicdguvu4d7b6yglgqvxo FOREIGN KEY (brand_id) REFERENCES public.brands(id),
                                          CONSTRAINT fk6x68tjj3eay19skqlhn7ls6ai FOREIGN KEY (category_id) REFERENCES public.categories(id)
);