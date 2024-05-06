--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.2

-- Started on 2024-05-06 09:31:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3628 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 245 (class 1259 OID 41310)
-- Name: contato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contato (
    id_contato integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.contato OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 41404)
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 41409)
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 41221)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresa (
    id_empresa integer NOT NULL,
    descricao text NOT NULL,
    id_usuario_criacao integer NOT NULL,
    data_criacao timestamp without time zone DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.empresa OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 41220)
-- Name: empresa_id_empresa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresa_id_empresa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empresa_id_empresa_seq OWNER TO postgres;

--
-- TOC entry 3629 (class 0 OID 0)
-- Dependencies: 237
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresa_id_empresa_seq OWNED BY public.empresa.id_empresa;


--
-- TOC entry 235 (class 1259 OID 41194)
-- Name: habilidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.habilidade (
    id_habilidade integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.habilidade OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 41193)
-- Name: habilidade_id_habilidade_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.habilidade_id_habilidade_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.habilidade_id_habilidade_seq OWNER TO postgres;

--
-- TOC entry 3630 (class 0 OID 0)
-- Dependencies: 234
-- Name: habilidade_id_habilidade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.habilidade_id_habilidade_seq OWNED BY public.habilidade.id_habilidade;


--
-- TOC entry 218 (class 1259 OID 40972)
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissao (
    id_permissao integer NOT NULL,
    nome text NOT NULL
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 40971)
-- Name: permissao_id_permissao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permissao_id_permissao_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permissao_id_permissao_seq OWNER TO postgres;

--
-- TOC entry 3631 (class 0 OID 0)
-- Dependencies: 217
-- Name: permissao_id_permissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permissao_id_permissao_seq OWNED BY public.permissao.id_permissao;


--
-- TOC entry 223 (class 1259 OID 41042)
-- Name: postagem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem (
    id_postagem integer NOT NULL,
    id_usuario integer NOT NULL,
    id_tipo_postagem integer NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    descricao text NOT NULL,
    qtd_curtidas integer DEFAULT 0 NOT NULL,
    qtd_comentarios integer DEFAULT 0 NOT NULL,
    qtd_compartilhamentos integer DEFAULT 0 NOT NULL,
    qtd_visualizacoes integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.postagem OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 41086)
-- Name: postagem_comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_comentario (
    id_postagem integer NOT NULL,
    id_usuario integer NOT NULL,
    data_comentario timestamp without time zone DEFAULT CURRENT_DATE NOT NULL,
    descricao text NOT NULL,
    id_postagem_comentario integer NOT NULL,
    data_alteracao timestamp without time zone,
    id_comentario_pai integer
);


ALTER TABLE public.postagem_comentario OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 41085)
-- Name: postagem_comentario_id_postagem_comentario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_comentario_id_postagem_comentario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_comentario_id_postagem_comentario_seq OWNER TO postgres;

--
-- TOC entry 3632 (class 0 OID 0)
-- Dependencies: 225
-- Name: postagem_comentario_id_postagem_comentario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_comentario_id_postagem_comentario_seq OWNED BY public.postagem_comentario.id_postagem_comentario;


--
-- TOC entry 227 (class 1259 OID 41115)
-- Name: postagem_compartilhamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_compartilhamento (
    id_postagem integer NOT NULL,
    id_usuario integer NOT NULL,
    id_postagem_compartilhamento_tipo integer NOT NULL
);


ALTER TABLE public.postagem_compartilhamento OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 41277)
-- Name: postagem_compartilhamento_tipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_compartilhamento_tipo (
    id_postagem_compartilhamento_tipo integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.postagem_compartilhamento_tipo OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 41276)
-- Name: postagem_compartilhamento_tip_id_postagem_compartilhamento__seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_compartilhamento_tip_id_postagem_compartilhamento__seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_compartilhamento_tip_id_postagem_compartilhamento__seq OWNER TO postgres;

--
-- TOC entry 3633 (class 0 OID 0)
-- Dependencies: 241
-- Name: postagem_compartilhamento_tip_id_postagem_compartilhamento__seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_compartilhamento_tip_id_postagem_compartilhamento__seq OWNED BY public.postagem_compartilhamento_tipo.id_postagem_compartilhamento_tipo;


--
-- TOC entry 224 (class 1259 OID 41059)
-- Name: postagem_curtida; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_curtida (
    id_postagem integer NOT NULL,
    id_usuario integer NOT NULL,
    data_curtida timestamp without time zone DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.postagem_curtida OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 41041)
-- Name: postagem_id_postagem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_id_postagem_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_id_postagem_seq OWNER TO postgres;

--
-- TOC entry 3634 (class 0 OID 0)
-- Dependencies: 222
-- Name: postagem_id_postagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_id_postagem_seq OWNED BY public.postagem.id_postagem;


--
-- TOC entry 230 (class 1259 OID 41154)
-- Name: postagem_vaga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga (
    id_postagem_vaga integer NOT NULL,
    id_postagem integer NOT NULL,
    descricao_completa text NOT NULL,
    id_empresa integer NOT NULL,
    id_postagem_vaga_cargo integer NOT NULL,
    data_criacao timestamp without time zone NOT NULL,
    id_cidade integer NOT NULL,
    id_estado integer NOT NULL,
    id_modelo_trabalho integer NOT NULL,
    qtd_candidatos integer DEFAULT 0 NOT NULL,
    data_atualizacao timestamp without time zone,
    aberta boolean DEFAULT true NOT NULL,
    foto bytea
);


ALTER TABLE public.postagem_vaga OWNER TO postgres;

--
-- TOC entry 3635 (class 0 OID 0)
-- Dependencies: 230
-- Name: COLUMN postagem_vaga.id_cidade; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.postagem_vaga.id_cidade IS 'buscar do ibge';


--
-- TOC entry 3636 (class 0 OID 0)
-- Dependencies: 230
-- Name: COLUMN postagem_vaga.id_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.postagem_vaga.id_estado IS 'buscar do ibge';


--
-- TOC entry 3637 (class 0 OID 0)
-- Dependencies: 230
-- Name: COLUMN postagem_vaga.id_modelo_trabalho; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.postagem_vaga.id_modelo_trabalho IS 'enum no java';


--
-- TOC entry 250 (class 1259 OID 41372)
-- Name: postagem_vaga_candidato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_candidato (
    id_postagem_vaga integer NOT NULL,
    id_usuario_candidato integer NOT NULL,
    data_criacao timestamp without time zone DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.postagem_vaga_candidato OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 41241)
-- Name: postagem_vaga_cargo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_cargo (
    id_postagem_vaga_cargo integer NOT NULL,
    descricao text NOT NULL,
    id_usuario integer NOT NULL,
    data_criacao timestamp without time zone DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.postagem_vaga_cargo OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 41240)
-- Name: postagem_vaga_cargo_id_postagem_vaga_cargo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_vaga_cargo_id_postagem_vaga_cargo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_vaga_cargo_id_postagem_vaga_cargo_seq OWNER TO postgres;

--
-- TOC entry 3638 (class 0 OID 0)
-- Dependencies: 239
-- Name: postagem_vaga_cargo_id_postagem_vaga_cargo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_vaga_cargo_id_postagem_vaga_cargo_seq OWNED BY public.postagem_vaga_cargo.id_postagem_vaga_cargo;


--
-- TOC entry 236 (class 1259 OID 41205)
-- Name: postagem_vaga_habilidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_habilidade (
    id_postagem_vaga integer NOT NULL,
    id_habilidade integer NOT NULL
);


ALTER TABLE public.postagem_vaga_habilidade OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 41153)
-- Name: postagem_vaga_id_postagem_vaga_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_vaga_id_postagem_vaga_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_vaga_id_postagem_vaga_seq OWNER TO postgres;

--
-- TOC entry 3639 (class 0 OID 0)
-- Dependencies: 229
-- Name: postagem_vaga_id_postagem_vaga_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_vaga_id_postagem_vaga_seq OWNED BY public.postagem_vaga.id_postagem_vaga;


--
-- TOC entry 233 (class 1259 OID 41178)
-- Name: postagem_vaga_postagem_vaga_senioridade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_postagem_vaga_senioridade (
    id_postagem_vaga integer NOT NULL,
    id_postagem_vaga_senioridade integer NOT NULL
);


ALTER TABLE public.postagem_vaga_postagem_vaga_senioridade OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 41352)
-- Name: postagem_vaga_postagem_vaga_tipo_contato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_postagem_vaga_tipo_contato (
    id_postagem_vaga integer NOT NULL,
    id_postagem_vaga_tipo_contato integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.postagem_vaga_postagem_vaga_tipo_contato OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 41168)
-- Name: postagem_vaga_senioridade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_senioridade (
    id_postagem_vaga_senioridade integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.postagem_vaga_senioridade OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 41341)
-- Name: postagem_vaga_tipo_contato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postagem_vaga_tipo_contato (
    id_postagem_vaga_tipo_contato integer NOT NULL,
    descricao character varying NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.postagem_vaga_tipo_contato OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 41167)
-- Name: postagem_vaga_tipo_id_postagem_vaga_tipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.postagem_vaga_tipo_id_postagem_vaga_tipo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postagem_vaga_tipo_id_postagem_vaga_tipo_seq OWNER TO postgres;

--
-- TOC entry 3640 (class 0 OID 0)
-- Dependencies: 231
-- Name: postagem_vaga_tipo_id_postagem_vaga_tipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.postagem_vaga_tipo_id_postagem_vaga_tipo_seq OWNED BY public.postagem_vaga_senioridade.id_postagem_vaga_senioridade;


--
-- TOC entry 244 (class 1259 OID 41309)
-- Name: rede_social_id_rede_social_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rede_social_id_rede_social_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rede_social_id_rede_social_seq OWNER TO postgres;

--
-- TOC entry 3641 (class 0 OID 0)
-- Dependencies: 244
-- Name: rede_social_id_rede_social_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rede_social_id_rede_social_seq OWNED BY public.contato.id_contato;


--
-- TOC entry 221 (class 1259 OID 41032)
-- Name: tipo_postagem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_postagem (
    id_tipo_postagem integer NOT NULL,
    descricao text NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.tipo_postagem OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 41031)
-- Name: tipo_postagem_id_tipo_postagem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_postagem_id_tipo_postagem_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_postagem_id_tipo_postagem_seq OWNER TO postgres;

--
-- TOC entry 3642 (class 0 OID 0)
-- Dependencies: 220
-- Name: tipo_postagem_id_tipo_postagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_postagem_id_tipo_postagem_seq OWNED BY public.tipo_postagem.id_tipo_postagem;


--
-- TOC entry 216 (class 1259 OID 40963)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    nome text NOT NULL,
    email text NOT NULL,
    cpf text NOT NULL,
    qtd_seguindo integer DEFAULT 0 NOT NULL,
    qtd_seguidores integer DEFAULT 0 NOT NULL,
    bio text,
    id_cidade integer,
    id_estado integer,
    senha text NOT NULL,
    usuario character varying NOT NULL,
    qtd_postagens integer DEFAULT 0 NOT NULL,
    foto_perfil bytea,
    email_visivel boolean DEFAULT true NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 41130)
-- Name: usuario_amigo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_amigo (
    id_usuario integer NOT NULL,
    id_usuario_conectado integer NOT NULL,
    data_conexao timestamp without time zone DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.usuario_amigo OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 41321)
-- Name: usuario_contato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_contato (
    id_usuario integer NOT NULL,
    id_contato integer NOT NULL,
    descricao text NOT NULL,
    publico boolean DEFAULT true NOT NULL
);


ALTER TABLE public.usuario_contato OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 41294)
-- Name: usuario_habilidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_habilidade (
    id_usuario integer NOT NULL,
    id_habilidade integer NOT NULL
);


ALTER TABLE public.usuario_habilidade OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 40962)
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 3643 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 219 (class 1259 OID 40982)
-- Name: usuario_permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_permissao (
    id_usuario integer NOT NULL,
    id_permissao integer NOT NULL
);


ALTER TABLE public.usuario_permissao OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 41340)
-- Name: vaga_tipo_contato_id_tipo_contato_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaga_tipo_contato_id_tipo_contato_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaga_tipo_contato_id_tipo_contato_seq OWNER TO postgres;

--
-- TOC entry 3644 (class 0 OID 0)
-- Dependencies: 247
-- Name: vaga_tipo_contato_id_tipo_contato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaga_tipo_contato_id_tipo_contato_seq OWNED BY public.postagem_vaga_tipo_contato.id_postagem_vaga_tipo_contato;


--
-- TOC entry 3342 (class 2604 OID 41313)
-- Name: contato id_contato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contato ALTER COLUMN id_contato SET DEFAULT nextval('public.rede_social_id_rede_social_seq'::regclass);


--
-- TOC entry 3336 (class 2604 OID 41224)
-- Name: empresa id_empresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa ALTER COLUMN id_empresa SET DEFAULT nextval('public.empresa_id_empresa_seq'::regclass);


--
-- TOC entry 3334 (class 2604 OID 41197)
-- Name: habilidade id_habilidade; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habilidade ALTER COLUMN id_habilidade SET DEFAULT nextval('public.habilidade_id_habilidade_seq'::regclass);


--
-- TOC entry 3316 (class 2604 OID 40975)
-- Name: permissao id_permissao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao ALTER COLUMN id_permissao SET DEFAULT nextval('public.permissao_id_permissao_seq'::regclass);


--
-- TOC entry 3319 (class 2604 OID 41045)
-- Name: postagem id_postagem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem ALTER COLUMN id_postagem SET DEFAULT nextval('public.postagem_id_postagem_seq'::regclass);


--
-- TOC entry 3327 (class 2604 OID 41090)
-- Name: postagem_comentario id_postagem_comentario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_comentario ALTER COLUMN id_postagem_comentario SET DEFAULT nextval('public.postagem_comentario_id_postagem_comentario_seq'::regclass);


--
-- TOC entry 3340 (class 2604 OID 41280)
-- Name: postagem_compartilhamento_tipo id_postagem_compartilhamento_tipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento_tipo ALTER COLUMN id_postagem_compartilhamento_tipo SET DEFAULT nextval('public.postagem_compartilhamento_tip_id_postagem_compartilhamento__seq'::regclass);


--
-- TOC entry 3329 (class 2604 OID 41157)
-- Name: postagem_vaga id_postagem_vaga; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga ALTER COLUMN id_postagem_vaga SET DEFAULT nextval('public.postagem_vaga_id_postagem_vaga_seq'::regclass);


--
-- TOC entry 3338 (class 2604 OID 41244)
-- Name: postagem_vaga_cargo id_postagem_vaga_cargo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_cargo ALTER COLUMN id_postagem_vaga_cargo SET DEFAULT nextval('public.postagem_vaga_cargo_id_postagem_vaga_cargo_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 41171)
-- Name: postagem_vaga_senioridade id_postagem_vaga_senioridade; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_senioridade ALTER COLUMN id_postagem_vaga_senioridade SET DEFAULT nextval('public.postagem_vaga_tipo_id_postagem_vaga_tipo_seq'::regclass);


--
-- TOC entry 3345 (class 2604 OID 41344)
-- Name: postagem_vaga_tipo_contato id_postagem_vaga_tipo_contato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_tipo_contato ALTER COLUMN id_postagem_vaga_tipo_contato SET DEFAULT nextval('public.vaga_tipo_contato_id_tipo_contato_seq'::regclass);


--
-- TOC entry 3317 (class 2604 OID 41035)
-- Name: tipo_postagem id_tipo_postagem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_postagem ALTER COLUMN id_tipo_postagem SET DEFAULT nextval('public.tipo_postagem_id_tipo_postagem_seq'::regclass);


--
-- TOC entry 3311 (class 2604 OID 40966)
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- TOC entry 3615 (class 0 OID 41310)
-- Dependencies: 245
-- Data for Name: contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contato (id_contato, descricao, ativo) FROM stdin;
1	INSTAGRAM	t
2	LINKEDIN	t
3	FACEBOOK	t
4	TIKTOK	t
5	SITE	t
6	EMAIL	t
7	WHATSAPP	t
\.


--
-- TOC entry 3621 (class 0 OID 41404)
-- Dependencies: 251
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1	maicon	db/changelog/migrations/db.changelog-1.0.xml	2024-05-05 21:05:01.677189	1	EXECUTED	9:46af35eb2450bd1455410680231c0272	sql		\N	4.24.0	\N	\N	4953901645
\.


--
-- TOC entry 3622 (class 0 OID 41409)
-- Dependencies: 252
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- TOC entry 3608 (class 0 OID 41221)
-- Dependencies: 238
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empresa (id_empresa, descricao, id_usuario_criacao, data_criacao) FROM stdin;
\.


--
-- TOC entry 3605 (class 0 OID 41194)
-- Dependencies: 235
-- Data for Name: habilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.habilidade (id_habilidade, descricao, ativo) FROM stdin;
1	JAVA	t
2	.NET	t
3	C#	t
4	REACT	t
5	PYTHON	t
6	ANGULAR	t
\.


--
-- TOC entry 3588 (class 0 OID 40972)
-- Dependencies: 218
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissao (id_permissao, nome) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USUARIO
\.


--
-- TOC entry 3593 (class 0 OID 41042)
-- Dependencies: 223
-- Data for Name: postagem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem (id_postagem, id_usuario, id_tipo_postagem, ativo, descricao, qtd_curtidas, qtd_comentarios, qtd_compartilhamentos, qtd_visualizacoes) FROM stdin;
\.


--
-- TOC entry 3596 (class 0 OID 41086)
-- Dependencies: 226
-- Data for Name: postagem_comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_comentario (id_postagem, id_usuario, data_comentario, descricao, id_postagem_comentario, data_alteracao, id_comentario_pai) FROM stdin;
\.


--
-- TOC entry 3597 (class 0 OID 41115)
-- Dependencies: 227
-- Data for Name: postagem_compartilhamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_compartilhamento (id_postagem, id_usuario, id_postagem_compartilhamento_tipo) FROM stdin;
\.


--
-- TOC entry 3612 (class 0 OID 41277)
-- Dependencies: 242
-- Data for Name: postagem_compartilhamento_tipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_compartilhamento_tipo (id_postagem_compartilhamento_tipo, descricao, ativo) FROM stdin;
1	COPIAR LINK	t
2	LINKEDIN	t
3	INSTAGRAM	t
4	SEU FEED	t
\.


--
-- TOC entry 3594 (class 0 OID 41059)
-- Dependencies: 224
-- Data for Name: postagem_curtida; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_curtida (id_postagem, id_usuario, data_curtida) FROM stdin;
\.


--
-- TOC entry 3600 (class 0 OID 41154)
-- Dependencies: 230
-- Data for Name: postagem_vaga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga (id_postagem_vaga, id_postagem, descricao_completa, id_empresa, id_postagem_vaga_cargo, data_criacao, id_cidade, id_estado, id_modelo_trabalho, qtd_candidatos, data_atualizacao, aberta, foto) FROM stdin;
\.


--
-- TOC entry 3620 (class 0 OID 41372)
-- Dependencies: 250
-- Data for Name: postagem_vaga_candidato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_candidato (id_postagem_vaga, id_usuario_candidato, data_criacao) FROM stdin;
\.


--
-- TOC entry 3610 (class 0 OID 41241)
-- Dependencies: 240
-- Data for Name: postagem_vaga_cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_cargo (id_postagem_vaga_cargo, descricao, id_usuario, data_criacao) FROM stdin;
\.


--
-- TOC entry 3606 (class 0 OID 41205)
-- Dependencies: 236
-- Data for Name: postagem_vaga_habilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_habilidade (id_postagem_vaga, id_habilidade) FROM stdin;
\.


--
-- TOC entry 3603 (class 0 OID 41178)
-- Dependencies: 233
-- Data for Name: postagem_vaga_postagem_vaga_senioridade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_postagem_vaga_senioridade (id_postagem_vaga, id_postagem_vaga_senioridade) FROM stdin;
\.


--
-- TOC entry 3619 (class 0 OID 41352)
-- Dependencies: 249
-- Data for Name: postagem_vaga_postagem_vaga_tipo_contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_postagem_vaga_tipo_contato (id_postagem_vaga, id_postagem_vaga_tipo_contato, descricao, ativo) FROM stdin;
\.


--
-- TOC entry 3602 (class 0 OID 41168)
-- Dependencies: 232
-- Data for Name: postagem_vaga_senioridade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_senioridade (id_postagem_vaga_senioridade, descricao, ativo) FROM stdin;
2	Júnior	t
3	Pleno	t
4	Sênior	t
5	Estágio	t
1	Programa de Estágio	t
\.


--
-- TOC entry 3618 (class 0 OID 41341)
-- Dependencies: 248
-- Data for Name: postagem_vaga_tipo_contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.postagem_vaga_tipo_contato (id_postagem_vaga_tipo_contato, descricao, ativo) FROM stdin;
2	LINK	t
3	EMAIL	t
4	WHATSAPP	t
5	TELEFONE	t
\.


--
-- TOC entry 3591 (class 0 OID 41032)
-- Dependencies: 221
-- Data for Name: tipo_postagem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_postagem (id_tipo_postagem, descricao, ativo) FROM stdin;
1	Curso	t
2	Vaga	t
\.


--
-- TOC entry 3586 (class 0 OID 40963)
-- Dependencies: 216
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, nome, email, cpf, qtd_seguindo, qtd_seguidores, bio, id_cidade, id_estado, senha, usuario, qtd_postagens, foto_perfil, email_visivel) FROM stdin;
\.


--
-- TOC entry 3598 (class 0 OID 41130)
-- Dependencies: 228
-- Data for Name: usuario_amigo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_amigo (id_usuario, id_usuario_conectado, data_conexao) FROM stdin;
\.


--
-- TOC entry 3616 (class 0 OID 41321)
-- Dependencies: 246
-- Data for Name: usuario_contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_contato (id_usuario, id_contato, descricao, publico) FROM stdin;
\.


--
-- TOC entry 3613 (class 0 OID 41294)
-- Dependencies: 243
-- Data for Name: usuario_habilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_habilidade (id_usuario, id_habilidade) FROM stdin;
\.


--
-- TOC entry 3589 (class 0 OID 40982)
-- Dependencies: 219
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_permissao (id_usuario, id_permissao) FROM stdin;
\.


--
-- TOC entry 3645 (class 0 OID 0)
-- Dependencies: 237
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresa_id_empresa_seq', 1, false);


--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 234
-- Name: habilidade_id_habilidade_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.habilidade_id_habilidade_seq', 6, true);


--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 217
-- Name: permissao_id_permissao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permissao_id_permissao_seq', 2, true);


--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 225
-- Name: postagem_comentario_id_postagem_comentario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_comentario_id_postagem_comentario_seq', 1, false);


--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 241
-- Name: postagem_compartilhamento_tip_id_postagem_compartilhamento__seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_compartilhamento_tip_id_postagem_compartilhamento__seq', 4, true);


--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 222
-- Name: postagem_id_postagem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_id_postagem_seq', 1, false);


--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 239
-- Name: postagem_vaga_cargo_id_postagem_vaga_cargo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_vaga_cargo_id_postagem_vaga_cargo_seq', 1, false);


--
-- TOC entry 3652 (class 0 OID 0)
-- Dependencies: 229
-- Name: postagem_vaga_id_postagem_vaga_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_vaga_id_postagem_vaga_seq', 1, false);


--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 231
-- Name: postagem_vaga_tipo_id_postagem_vaga_tipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.postagem_vaga_tipo_id_postagem_vaga_tipo_seq', 5, true);


--
-- TOC entry 3654 (class 0 OID 0)
-- Dependencies: 244
-- Name: rede_social_id_rede_social_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rede_social_id_rede_social_seq', 7, true);


--
-- TOC entry 3655 (class 0 OID 0)
-- Dependencies: 220
-- Name: tipo_postagem_id_tipo_postagem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_postagem_id_tipo_postagem_seq', 2, true);


--
-- TOC entry 3656 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 1, false);


--
-- TOC entry 3657 (class 0 OID 0)
-- Dependencies: 247
-- Name: vaga_tipo_contato_id_tipo_contato_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaga_tipo_contato_id_tipo_contato_seq', 5, true);


--
-- TOC entry 3396 (class 2606 OID 41389)
-- Name: contato contato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contato
    ADD CONSTRAINT contato_pk PRIMARY KEY (id_contato);


--
-- TOC entry 3398 (class 2606 OID 41391)
-- Name: contato contato_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contato
    ADD CONSTRAINT contato_unique UNIQUE (descricao);


--
-- TOC entry 3410 (class 2606 OID 41413)
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 40970)
-- Name: usuario email_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT email_unique UNIQUE (email);


--
-- TOC entry 3384 (class 2606 OID 41229)
-- Name: empresa empresa_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pk PRIMARY KEY (id_empresa);


--
-- TOC entry 3378 (class 2606 OID 41202)
-- Name: habilidade habilidade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habilidade
    ADD CONSTRAINT habilidade_pk PRIMARY KEY (id_habilidade);


--
-- TOC entry 3380 (class 2606 OID 41204)
-- Name: habilidade habilidade_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habilidade
    ADD CONSTRAINT habilidade_unique UNIQUE (descricao);


--
-- TOC entry 3354 (class 2606 OID 40981)
-- Name: permissao permissao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_pk PRIMARY KEY (id_permissao);


--
-- TOC entry 3356 (class 2606 OID 40979)
-- Name: permissao permissao_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_unique UNIQUE (nome);


--
-- TOC entry 3366 (class 2606 OID 41094)
-- Name: postagem_comentario postagem_comentario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_comentario
    ADD CONSTRAINT postagem_comentario_pk PRIMARY KEY (id_postagem_comentario);


--
-- TOC entry 3368 (class 2606 OID 41119)
-- Name: postagem_compartilhamento postagem_compartilhamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento
    ADD CONSTRAINT postagem_compartilhamento_pk PRIMARY KEY (id_postagem, id_usuario);


--
-- TOC entry 3390 (class 2606 OID 41285)
-- Name: postagem_compartilhamento_tipo postagem_compartilhamento_tipo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento_tipo
    ADD CONSTRAINT postagem_compartilhamento_tipo_pk PRIMARY KEY (id_postagem_compartilhamento_tipo);


--
-- TOC entry 3392 (class 2606 OID 41287)
-- Name: postagem_compartilhamento_tipo postagem_compartilhamento_tipo_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento_tipo
    ADD CONSTRAINT postagem_compartilhamento_tipo_unique UNIQUE (descricao);


--
-- TOC entry 3364 (class 2606 OID 41064)
-- Name: postagem_curtida postagem_curtida_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_curtida
    ADD CONSTRAINT postagem_curtida_pk PRIMARY KEY (id_postagem, id_usuario);


--
-- TOC entry 3362 (class 2606 OID 41048)
-- Name: postagem postagem_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem
    ADD CONSTRAINT postagem_pk PRIMARY KEY (id_postagem);


--
-- TOC entry 3408 (class 2606 OID 41377)
-- Name: postagem_vaga_candidato postagem_vaga_candidato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_candidato
    ADD CONSTRAINT postagem_vaga_candidato_pk PRIMARY KEY (id_postagem_vaga, id_usuario_candidato);


--
-- TOC entry 3386 (class 2606 OID 41249)
-- Name: postagem_vaga_cargo postagem_vaga_cargo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_cargo
    ADD CONSTRAINT postagem_vaga_cargo_pk PRIMARY KEY (id_postagem_vaga_cargo);


--
-- TOC entry 3388 (class 2606 OID 41251)
-- Name: postagem_vaga_cargo postagem_vaga_cargo_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_cargo
    ADD CONSTRAINT postagem_vaga_cargo_unique UNIQUE (descricao);


--
-- TOC entry 3382 (class 2606 OID 41209)
-- Name: postagem_vaga_habilidade postagem_vaga_habilidade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_habilidade
    ADD CONSTRAINT postagem_vaga_habilidade_pk PRIMARY KEY (id_postagem_vaga, id_habilidade);


--
-- TOC entry 3372 (class 2606 OID 41161)
-- Name: postagem_vaga postagem_vaga_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga
    ADD CONSTRAINT postagem_vaga_pk PRIMARY KEY (id_postagem_vaga);


--
-- TOC entry 3376 (class 2606 OID 41263)
-- Name: postagem_vaga_postagem_vaga_senioridade postagem_vaga_postagem_vaga_senioridade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_senioridade
    ADD CONSTRAINT postagem_vaga_postagem_vaga_senioridade_pk PRIMARY KEY (id_postagem_vaga, id_postagem_vaga_senioridade);


--
-- TOC entry 3406 (class 2606 OID 41359)
-- Name: postagem_vaga_postagem_vaga_tipo_contato postagem_vaga_postagem_vaga_tipo_contato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_tipo_contato
    ADD CONSTRAINT postagem_vaga_postagem_vaga_tipo_contato_pk PRIMARY KEY (id_postagem_vaga, id_postagem_vaga_tipo_contato);


--
-- TOC entry 3374 (class 2606 OID 41270)
-- Name: postagem_vaga_senioridade postagem_vaga_senioridade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_senioridade
    ADD CONSTRAINT postagem_vaga_senioridade_pk PRIMARY KEY (id_postagem_vaga_senioridade);


--
-- TOC entry 3360 (class 2606 OID 41040)
-- Name: tipo_postagem tipo_postagem_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_postagem
    ADD CONSTRAINT tipo_postagem_pk PRIMARY KEY (id_tipo_postagem);


--
-- TOC entry 3370 (class 2606 OID 41135)
-- Name: usuario_amigo usuario_amigo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_amigo
    ADD CONSTRAINT usuario_amigo_pk PRIMARY KEY (id_usuario, id_usuario_conectado);


--
-- TOC entry 3400 (class 2606 OID 41393)
-- Name: usuario_contato usuario_contato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_contato
    ADD CONSTRAINT usuario_contato_pk PRIMARY KEY (id_usuario, id_contato);


--
-- TOC entry 3394 (class 2606 OID 41298)
-- Name: usuario_habilidade usuario_habilidade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_pk PRIMARY KEY (id_usuario, id_habilidade);


--
-- TOC entry 3358 (class 2606 OID 41010)
-- Name: usuario_permissao usuario_permissao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT usuario_permissao_pk PRIMARY KEY (id_usuario, id_permissao);


--
-- TOC entry 3352 (class 2606 OID 40990)
-- Name: usuario usuario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id_usuario);


--
-- TOC entry 3402 (class 2606 OID 41349)
-- Name: postagem_vaga_tipo_contato vaga_tipo_contato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_tipo_contato
    ADD CONSTRAINT vaga_tipo_contato_pk PRIMARY KEY (id_postagem_vaga_tipo_contato);


--
-- TOC entry 3404 (class 2606 OID 41351)
-- Name: postagem_vaga_tipo_contato vaga_tipo_contato_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_tipo_contato
    ADD CONSTRAINT vaga_tipo_contato_unique UNIQUE (descricao);


--
-- TOC entry 3432 (class 2606 OID 41230)
-- Name: empresa empresa_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_usuario_fk FOREIGN KEY (id_usuario_criacao) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3417 (class 2606 OID 41148)
-- Name: postagem_comentario postagem_comentario_postagem_comentario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_comentario
    ADD CONSTRAINT postagem_comentario_postagem_comentario_fk FOREIGN KEY (id_comentario_pai) REFERENCES public.postagem_comentario(id_postagem_comentario);


--
-- TOC entry 3418 (class 2606 OID 41095)
-- Name: postagem_comentario postagem_comentario_postagem_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_comentario
    ADD CONSTRAINT postagem_comentario_postagem_fk FOREIGN KEY (id_postagem) REFERENCES public.postagem(id_postagem);


--
-- TOC entry 3419 (class 2606 OID 41100)
-- Name: postagem_comentario postagem_comentario_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_comentario
    ADD CONSTRAINT postagem_comentario_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3420 (class 2606 OID 41288)
-- Name: postagem_compartilhamento postagem_compartilhamento_postagem_compartilhamento_tipo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento
    ADD CONSTRAINT postagem_compartilhamento_postagem_compartilhamento_tipo_fk FOREIGN KEY (id_postagem_compartilhamento_tipo) REFERENCES public.postagem_compartilhamento_tipo(id_postagem_compartilhamento_tipo);


--
-- TOC entry 3421 (class 2606 OID 41125)
-- Name: postagem_compartilhamento postagem_compartilhamento_postagem_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento
    ADD CONSTRAINT postagem_compartilhamento_postagem_fk FOREIGN KEY (id_postagem) REFERENCES public.postagem(id_postagem);


--
-- TOC entry 3422 (class 2606 OID 41120)
-- Name: postagem_compartilhamento postagem_compartilhamento_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_compartilhamento
    ADD CONSTRAINT postagem_compartilhamento_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3415 (class 2606 OID 41065)
-- Name: postagem_curtida postagem_curtida_postagem_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_curtida
    ADD CONSTRAINT postagem_curtida_postagem_fk FOREIGN KEY (id_postagem) REFERENCES public.postagem(id_postagem);


--
-- TOC entry 3416 (class 2606 OID 41070)
-- Name: postagem_curtida postagem_curtida_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_curtida
    ADD CONSTRAINT postagem_curtida_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3413 (class 2606 OID 41054)
-- Name: postagem postagem_tipo_postagem_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem
    ADD CONSTRAINT postagem_tipo_postagem_fk FOREIGN KEY (id_tipo_postagem) REFERENCES public.tipo_postagem(id_tipo_postagem);


--
-- TOC entry 3414 (class 2606 OID 41049)
-- Name: postagem postagem_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem
    ADD CONSTRAINT postagem_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3440 (class 2606 OID 41378)
-- Name: postagem_vaga_candidato postagem_vaga_candidato_postagem_vaga_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_candidato
    ADD CONSTRAINT postagem_vaga_candidato_postagem_vaga_fk FOREIGN KEY (id_postagem_vaga) REFERENCES public.postagem_vaga(id_postagem_vaga);


--
-- TOC entry 3441 (class 2606 OID 41383)
-- Name: postagem_vaga_candidato postagem_vaga_candidato_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_candidato
    ADD CONSTRAINT postagem_vaga_candidato_usuario_fk FOREIGN KEY (id_usuario_candidato) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3433 (class 2606 OID 41252)
-- Name: postagem_vaga_cargo postagem_vaga_cargo_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_cargo
    ADD CONSTRAINT postagem_vaga_cargo_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3425 (class 2606 OID 41235)
-- Name: postagem_vaga postagem_vaga_empresa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga
    ADD CONSTRAINT postagem_vaga_empresa_fk FOREIGN KEY (id_empresa) REFERENCES public.empresa(id_empresa);


--
-- TOC entry 3430 (class 2606 OID 41215)
-- Name: postagem_vaga_habilidade postagem_vaga_habilidade_habilidade_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_habilidade
    ADD CONSTRAINT postagem_vaga_habilidade_habilidade_fk FOREIGN KEY (id_habilidade) REFERENCES public.habilidade(id_habilidade);


--
-- TOC entry 3431 (class 2606 OID 41210)
-- Name: postagem_vaga_habilidade postagem_vaga_habilidade_postagem_vaga_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_habilidade
    ADD CONSTRAINT postagem_vaga_habilidade_postagem_vaga_fk FOREIGN KEY (id_postagem_vaga) REFERENCES public.postagem_vaga(id_postagem_vaga);


--
-- TOC entry 3426 (class 2606 OID 41162)
-- Name: postagem_vaga postagem_vaga_postagem_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga
    ADD CONSTRAINT postagem_vaga_postagem_fk FOREIGN KEY (id_postagem) REFERENCES public.postagem(id_postagem);


--
-- TOC entry 3427 (class 2606 OID 41257)
-- Name: postagem_vaga postagem_vaga_postagem_vaga_cargo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga
    ADD CONSTRAINT postagem_vaga_postagem_vaga_cargo_fk FOREIGN KEY (id_postagem_vaga_cargo) REFERENCES public.postagem_vaga_cargo(id_postagem_vaga_cargo);


--
-- TOC entry 3428 (class 2606 OID 41264)
-- Name: postagem_vaga_postagem_vaga_senioridade postagem_vaga_postagem_vaga_senioridade_postagem_vaga_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_senioridade
    ADD CONSTRAINT postagem_vaga_postagem_vaga_senioridade_postagem_vaga_fk FOREIGN KEY (id_postagem_vaga) REFERENCES public.postagem_vaga(id_postagem_vaga);


--
-- TOC entry 3429 (class 2606 OID 41271)
-- Name: postagem_vaga_postagem_vaga_senioridade postagem_vaga_postagem_vaga_senioridade_postagem_vaga_seniorida; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_senioridade
    ADD CONSTRAINT postagem_vaga_postagem_vaga_senioridade_postagem_vaga_seniorida FOREIGN KEY (id_postagem_vaga_senioridade) REFERENCES public.postagem_vaga_senioridade(id_postagem_vaga_senioridade);


--
-- TOC entry 3438 (class 2606 OID 41360)
-- Name: postagem_vaga_postagem_vaga_tipo_contato postagem_vaga_postagem_vaga_tipo_contato_postagem_vaga_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_tipo_contato
    ADD CONSTRAINT postagem_vaga_postagem_vaga_tipo_contato_postagem_vaga_fk FOREIGN KEY (id_postagem_vaga) REFERENCES public.postagem_vaga(id_postagem_vaga);


--
-- TOC entry 3439 (class 2606 OID 41365)
-- Name: postagem_vaga_postagem_vaga_tipo_contato postagem_vaga_postagem_vaga_tipo_contato_postagem_vaga_tipo_con; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postagem_vaga_postagem_vaga_tipo_contato
    ADD CONSTRAINT postagem_vaga_postagem_vaga_tipo_contato_postagem_vaga_tipo_con FOREIGN KEY (id_postagem_vaga_tipo_contato) REFERENCES public.postagem_vaga_tipo_contato(id_postagem_vaga_tipo_contato);


--
-- TOC entry 3423 (class 2606 OID 41141)
-- Name: usuario_amigo usuario_amigo_usuario_conectado_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_amigo
    ADD CONSTRAINT usuario_amigo_usuario_conectado_fk_1 FOREIGN KEY (id_usuario_conectado) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3424 (class 2606 OID 41136)
-- Name: usuario_amigo usuario_amigo_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_amigo
    ADD CONSTRAINT usuario_amigo_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3436 (class 2606 OID 41394)
-- Name: usuario_contato usuario_contato_contato_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_contato
    ADD CONSTRAINT usuario_contato_contato_fk FOREIGN KEY (id_contato) REFERENCES public.contato(id_contato);


--
-- TOC entry 3437 (class 2606 OID 41399)
-- Name: usuario_contato usuario_contato_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_contato
    ADD CONSTRAINT usuario_contato_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3434 (class 2606 OID 41304)
-- Name: usuario_habilidade usuario_habilidade_habilidade_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_habilidade_fk FOREIGN KEY (id_habilidade) REFERENCES public.habilidade(id_habilidade);


--
-- TOC entry 3435 (class 2606 OID 41299)
-- Name: usuario_habilidade usuario_habilidade_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3411 (class 2606 OID 41016)
-- Name: usuario_permissao usuario_permissao_permissao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT usuario_permissao_permissao_fk FOREIGN KEY (id_permissao) REFERENCES public.permissao(id_permissao);


--
-- TOC entry 3412 (class 2606 OID 41011)
-- Name: usuario_permissao usuario_permissao_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT usuario_permissao_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


-- Completed on 2024-05-06 09:31:23

--
-- PostgreSQL database dump complete
--

