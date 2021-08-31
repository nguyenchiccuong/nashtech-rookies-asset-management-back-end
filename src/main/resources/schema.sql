--
-- TOC entry 200 (class 1259 OID 34364)
-- Name: assets; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.assets (
    assetcode character varying(255) NOT NULL,
    assetname character varying(255) NOT NULL,
    installdate timestamp without time zone NOT NULL,
    isdeleted boolean NOT NULL,
    specification character varying(255) NOT NULL,
    state smallint NOT NULL,
    categorycode character varying(255) NOT NULL,
    locationid bigint NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 34370)
-- Name: assignments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.assignments (
    assignmentid bigint NOT NULL,
    assigneddate timestamp without time zone NOT NULL,
    isdeleted boolean NOT NULL,
    note character varying(255),
    state smallint NOT NULL,
    assetcode character varying(255) NOT NULL,
    assignedby character varying(255) NOT NULL,
    assignedto character varying(255) NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 34376)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    categorycode character varying(255) NOT NULL,
    categoryname character varying(255) NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 34382)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 204 (class 1259 OID 34384)
-- Name: location_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.location_sequence
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 205 (class 1259 OID 34386)
-- Name: locations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.locations (
    locationid bigint NOT NULL,
    address character varying(255) NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 34389)
-- Name: requests; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.requests (
    requestid bigint NOT NULL,
    isdeleted boolean NOT NULL,
    returneddate timestamp without time zone,
    state smallint NOT NULL,
    acceptedby character varying(255),
    assignmentid bigint NOT NULL,
    requestedby character varying(255) NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 34395)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    roleid bigint NOT NULL,
    rolename character varying(255) NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 34398)
-- Name: staffcode_generator; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.staffcode_generator
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 209 (class 1259 OID 34400)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    staffcode character varying(255) NOT NULL,
    dateofbirth timestamp without time zone NOT NULL,
    firstlogin boolean NOT NULL,
    firstname character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    isdeleted boolean NOT NULL,
    joineddate timestamp without time zone NOT NULL,
    lastname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    locationid bigint NOT NULL,
    roleid bigint NOT NULL
);


--
-- TOC entry 2887 (class 2606 OID 34407)
-- Name: assets assets_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assets
    ADD CONSTRAINT assets_pkey PRIMARY KEY (assetcode);


--
-- TOC entry 2893 (class 2606 OID 34409)
-- Name: assignments assignments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignments
    ADD CONSTRAINT assignments_pkey PRIMARY KEY (assignmentid);


--
-- TOC entry 2895 (class 2606 OID 34411)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (categorycode);


--
-- TOC entry 2900 (class 2606 OID 34413)
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (locationid);


--
-- TOC entry 2908 (class 2606 OID 34415)
-- Name: requests requests_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (requestid);


--
-- TOC entry 2911 (class 2606 OID 34417)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (roleid);


--
-- TOC entry 2902 (class 2606 OID 34419)
-- Name: locations uk6ythtk02shyto7rh04jhxi4h9; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT uk6ythtk02shyto7rh04jhxi4h9 UNIQUE (address);


--
-- TOC entry 2898 (class 2606 OID 34421)
-- Name: categories ukpr2ms98ayaf1r17k7yyr4l3o9; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT ukpr2ms98ayaf1r17k7yyr4l3o9 UNIQUE (categoryname);


--
-- TOC entry 2913 (class 2606 OID 34423)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2918 (class 2606 OID 34425)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (staffcode);


--
-- TOC entry 2883 (class 1259 OID 34426)
-- Name: asset_category_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX asset_category_idx ON public.assets USING btree (categorycode);


--
-- TOC entry 2884 (class 1259 OID 34427)
-- Name: asset_name_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX asset_name_idx ON public.assets USING btree (assetname);


--
-- TOC entry 2885 (class 1259 OID 34428)
-- Name: asset_state_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX asset_state_idx ON public.assets USING btree (state);


--
-- TOC entry 2888 (class 1259 OID 34429)
-- Name: assignment_assetcode_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX assignment_assetcode_idx ON public.assignments USING btree (assetcode);


--
-- TOC entry 2889 (class 1259 OID 34430)
-- Name: assignment_assigneddate_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX assignment_assigneddate_idx ON public.assignments USING btree (assigneddate);


--
-- TOC entry 2890 (class 1259 OID 34431)
-- Name: assignment_assignedto_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX assignment_assignedto_idx ON public.assignments USING btree (assignedto);


--
-- TOC entry 2891 (class 1259 OID 34432)
-- Name: assignment_state_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX assignment_state_idx ON public.assignments USING btree (state);


--
-- TOC entry 2896 (class 1259 OID 34433)
-- Name: category_name_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX category_name_idx ON public.categories USING btree (categoryname);


--
-- TOC entry 2903 (class 1259 OID 34434)
-- Name: request_assignmentid_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX request_assignmentid_idx ON public.requests USING btree (assignmentid);


--
-- TOC entry 2904 (class 1259 OID 34435)
-- Name: request_requestedby_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX request_requestedby_idx ON public.requests USING btree (requestedby);


--
-- TOC entry 2905 (class 1259 OID 34436)
-- Name: request_returneddate_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX request_returneddate_idx ON public.requests USING btree (returneddate);


--
-- TOC entry 2906 (class 1259 OID 34437)
-- Name: request_state_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX request_state_idx ON public.requests USING btree (state);


--
-- TOC entry 2909 (class 1259 OID 34438)
-- Name: role_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX role_idx ON public.roles USING btree (rolename);


--
-- TOC entry 2914 (class 1259 OID 34439)
-- Name: user_location_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX user_location_idx ON public.users USING btree (locationid);


--
-- TOC entry 2915 (class 1259 OID 34440)
-- Name: user_role_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX user_role_idx ON public.users USING btree (roleid);


--
-- TOC entry 2916 (class 1259 OID 34441)
-- Name: user_username_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX user_username_idx ON public.users USING btree (username);


--
-- TOC entry 2924 (class 2606 OID 34442)
-- Name: requests fk1ha6o2qgpjwvu7xftxcfdw0eq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT fk1ha6o2qgpjwvu7xftxcfdw0eq FOREIGN KEY (requestedby) REFERENCES public.users(staffcode);


--
-- TOC entry 2921 (class 2606 OID 34447)
-- Name: assignments fk4d7j8tc7abcyqcnuswbv3ammf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignments
    ADD CONSTRAINT fk4d7j8tc7abcyqcnuswbv3ammf FOREIGN KEY (assetcode) REFERENCES public.assets(assetcode);


--
-- TOC entry 2919 (class 2606 OID 34452)
-- Name: assets fkbcv8pcymochk6ntks4kljh377; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assets
    ADD CONSTRAINT fkbcv8pcymochk6ntks4kljh377 FOREIGN KEY (categorycode) REFERENCES public.categories(categorycode);


--
-- TOC entry 2925 (class 2606 OID 34457)
-- Name: requests fke7a8ien8p1ywu34sk14kuyhp2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT fke7a8ien8p1ywu34sk14kuyhp2 FOREIGN KEY (acceptedby) REFERENCES public.users(staffcode);


--
-- TOC entry 2927 (class 2606 OID 34462)
-- Name: users fkgrhs0suhl8cbodxn47xadxp94; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkgrhs0suhl8cbodxn47xadxp94 FOREIGN KEY (roleid) REFERENCES public.roles(roleid);


--
-- TOC entry 2920 (class 2606 OID 34467)
-- Name: assets fkgrljitd2jsng6o7tjkgup5x33; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assets
    ADD CONSTRAINT fkgrljitd2jsng6o7tjkgup5x33 FOREIGN KEY (locationid) REFERENCES public.locations(locationid);


--
-- TOC entry 2922 (class 2606 OID 34472)
-- Name: assignments fkl6l8b6askq6oqrv4ray4gwbxc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignments
    ADD CONSTRAINT fkl6l8b6askq6oqrv4ray4gwbxc FOREIGN KEY (assignedto) REFERENCES public.users(staffcode);


--
-- TOC entry 2923 (class 2606 OID 34477)
-- Name: assignments fkmcdcffgmca59o2hi9fcskwcks; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignments
    ADD CONSTRAINT fkmcdcffgmca59o2hi9fcskwcks FOREIGN KEY (assignedby) REFERENCES public.users(staffcode);


--
-- TOC entry 2928 (class 2606 OID 34482)
-- Name: users fknbgih6spq8ryw49q0yyyfvee7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fknbgih6spq8ryw49q0yyyfvee7 FOREIGN KEY (locationid) REFERENCES public.locations(locationid);


--
-- TOC entry 2926 (class 2606 OID 34487)
-- Name: requests fkqpyo1upnqnraqpush9k502sph; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT fkqpyo1upnqnraqpush9k502sph FOREIGN KEY (assignmentid) REFERENCES public.assignments(assignmentid);


-- Completed on 2021-08-31 23:38:09

--
-- PostgreSQL database dump complete
--

