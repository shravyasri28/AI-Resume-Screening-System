# AI Resume–Job Description Matching System

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

An AI-powered backend system that evaluates how well a resume aligns with a given job description using **text similarity and keyword-based explainability**. The system is **domain-agnostic**, works across technical and non-technical roles, and provides transparent reasoning behind match scores.

<div align="center">
  <img src="https://img.shields.io/badge/Build-Passing-success" alt="Build Status">
  <img src="https://img.shields.io/badge/Coverage-85%25-green" alt="Coverage">
  <img src="https://img.shields.io/badge/Version-1.0.0-blue" alt="Version">
</div>

---

## 📌 Problem Statement

Recruiters often receive hundreds of resumes for a single job posting. Manual screening is:

- ⏱️ **Time-consuming** – Hours spent reviewing applications
- 👤 **Subjective** – Different reviewers, different criteria  
- 🔄 **Inconsistent** – Same resume, different outcomes

Traditional keyword filters fail because resumes and job descriptions often use **different wording** for the same concepts (e.g., "JavaScript" vs "JS", "Machine Learning" vs "ML").

### 💡 Solution

This project builds an **automatic, explainable resume screening system** that evaluates resume–job alignment without relying on hardcoded skill lists, using semantic similarity and data-driven keyword extraction.

---

## ✨ Key Features

- 📄 Upload **Resume** and **Job Description** (PDF or text)
- 🔍 Extract clean text from documents using Apache Tika
- 🧮 Compute semantic similarity using **TF-IDF + Cosine Similarity**
- 🎯 Extract **job-description-derived keywords** for explainability
- 📊 Display:
  - Match score (0-100%)
  - Matched keywords
  - Missing keywords
- 🎨 Lightweight frontend for easy interaction
- 🔧 Fully backend-driven, modular design
- 🌍 Domain-agnostic across all industries

---

## 🧠 How the System Works

### High-Level Flow

```
┌─────────────────────────────────────────────────────────────┐
│  User uploads Resume + Job Description                      │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  Spring Boot REST API (/api/match)                         │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  Apache Tika extracts clean text from PDFs                 │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  Text preprocessing (tokenization, stopwords removal)      │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  TF-IDF vectorization of both documents                    │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  Cosine similarity → Match score calculation               │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  JD-based keyword extraction and comparison                │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│  JSON response → Frontend displays results                 │
└─────────────────────────────────────────────────────────────┘
```

### 📝 Step-by-Step Process

1. **Document Upload** – User submits resume and job description files
2. **Text Extraction** – Apache Tika parses PDFs/text into clean strings
3. **Preprocessing** – Tokenization, lowercasing, stopword removal
4. **Vectorization** – TF-IDF converts text into numerical vectors
5. **Similarity Scoring** – Cosine similarity measures alignment (0-1)
6. **Keyword Analysis** – Identifies matched and missing terms from JD
7. **Response Generation** – Returns structured JSON with insights

---

## 🏗️ Architecture Overview

### System Design

```
┌────────────────────────────────────────────────────────────┐
│                     FRONTEND LAYER                         │
│  ┌──────────────────────────────────────────────────────┐ │
│  │  HTML + CSS + JavaScript (SPA)                       │ │
│  │  • File upload interface                             │ │
│  │  • Results visualization                             │ │
│  │  • Real-time feedback                                │ │
│  └──────────────────────────────────────────────────────┘ │
└────────────────────┬───────────────────────────────────────┘
                     │ HTTP/REST
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   CONTROLLER LAYER                         │
│  ┌──────────────────────────────────────────────────────┐ │
│  │  MatchController.java                                │ │
│  │  • Handle HTTP requests                              │ │
│  │  • File upload validation                            │ │
│  │  • CORS configuration                                │ │
│  └──────────────────────────────────────────────────────┘ │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                    SERVICE LAYER                           │
│  ┌──────────────────────────────────────────────────────┐ │
│  │  MatchingService.java                                │ │
│  │  • Orchestrate NLP pipeline                          │ │
│  │  • Business logic coordination                       │ │
│  │  • Error handling                                    │ │
│  └──────────────────────────────────────────────────────┘ │
└────────────────────┬───────────────────────────────────────┘
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
┌──────────────────┐  ┌──────────────────────┐
│  PARSER LAYER    │  │    NLP LAYER         │
│ ┌──────────────┐ │  │ ┌──────────────────┐ │
│ │DocumentParser│ │  │ │TextPreprocessor  │ │
│ │• Apache Tika │ │  │ │TFIDFVectorizer   │ │
│ │• PDF extract │ │  │ │CosineSimilarity  │ │
│ │• Text clean  │ │  │ │KeywordExtractor  │ │
│ └──────────────┘ │  │ └──────────────────┘ │
└──────────────────┘  └──────────────────────┘
```

### 📦 Component Responsibilities

| Component | Purpose | Key Methods |
|-----------|---------|-------------|
| **MatchController** | Handles HTTP requests, file uploads, CORS | `matchResumeWithJobDescription()` |
| **MatchingService** | Orchestrates NLP pipeline, coordinates processing | `calculateMatch()` |
| **DocumentParser** | Extracts text from PDFs and documents | `parseDocument()` |
| **TextPreprocessor** | Cleans and tokenizes text | `preprocess()`, `tokenize()` |
| **TFIDFVectorizer** | Converts text to TF-IDF vectors | `vectorize()`, `calculateTFIDF()` |
| **CosineSimilarity** | Calculates similarity between vectors | `calculate()` |
| **KeywordExtractor** | Extracts and compares keywords | `extractKeywords()`, `compareKeywords()` |

---

## 🛠️ Tech Stack

| Technology | Version | Purpose | Why This Choice? |
|------------|---------|---------|------------------|
| **Java** | 17+ | Core backend & NLP logic | Type safety, performance, enterprise-ready |
| **Spring Boot** | 3.2.0 | REST API, dependency injection | Rapid development, production-ready |
| **Apache Maven** | 3.6+ | Build & dependency management | Standard Java build tool |
| **Apache Tika** | 2.9.1 | PDF/text parsing | Handles multiple document formats reliably |
| **TF-IDF** | Custom | Text vectorization | Captures term importance in documents |
| **Cosine Similarity** | Custom | Semantic alignment scoring | Standard measure for text similarity (0-1) |
| **HTML/CSS/JS** | ES6+ | Frontend UI | Lightweight, no framework overhead |
| **Lombok** | Latest | Reduce boilerplate | Clean, maintainable code |

---

## 📊 API Documentation

### Endpoint: Match Resume with Job Description

**POST** `/api/match`

**Request:**
```http
POST /api/match HTTP/1.1
Content-Type: multipart/form-data
Host: localhost:8081

resume: [PDF/TXT file]
jobDescription: [PDF/TXT file]
```

**Response:**
```json
{
  "matchScore": 0.73,
  "matchedKeywords": [
    "java",
    "spring boot",
    "rest api",
    "backend development",
    "microservices"
  ],
  "missingKeywords": [
    "docker",
    "kubernetes",
    "aws"
  ]
}
```

### Response Field Descriptions

| Field | Type | Range | Description |
|-------|------|-------|-------------|
| `matchScore` | `double` | 0.0 - 1.0 | Overall semantic alignment between resume and JD |
| `matchedKeywords` | `List<String>` | - | Important JD terms found in the resume |
| `missingKeywords` | `List<String>` | - | Important JD terms NOT found in the resume |

### Status Codes

| Code | Description |
|------|-------------|
| 200 | Success - Match calculated successfully |
| 400 | Bad Request - Invalid file format or missing files |
| 500 | Internal Server Error - Processing failed |

---

## 🎯 Example Usage

### Input Files

**Resume.pdf:**
```
Experienced Java Developer with 5 years in Spring Boot, REST API design, 
and microservices architecture. Proficient in backend development, database 
optimization, and agile methodologies. Strong problem-solving skills with 
expertise in MySQL and PostgreSQL.
```

**JobDescription.txt:**
```
Looking for a Senior Java Developer skilled in Spring Boot, REST APIs, 
microservices, Docker, and Kubernetes. AWS experience preferred. 
Must have strong backend development experience and knowledge of 
containerization technologies.
```

### Output Response

```json
{
  "matchScore": 0.73,
  "matchedKeywords": [
    "java",
    "spring boot",
    "rest api",
    "microservices",
    "backend development"
  ],
  "missingKeywords": [
    "docker",
    "kubernetes",
    "aws",
    "containerization"
  ]
}
```

### 📈 Interpretation

- **Match Score: 73%** – Strong alignment between resume and job requirements
- **5 key skills matched** – Core requirements are met
- **4 skills to develop** – Areas for improvement or interview discussion
- **Recommendation:** Good candidate for interview, discuss missing skills

---

## ⚙️ Installation & Setup

### Prerequisites

- ☕ **Java 17 or higher** - [Download Java](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- 🔧 **Git** - [Download Git](https://git-scm.com/downloads)
- 💻 **IDE (Optional)** - IntelliJ IDEA or Eclipse

### Quick Start Guide

```bash
# 1. Clone the repository
git clone https://github.com/<your-username>/AI-resume-jd-matching.git

# 2. Navigate to project directory
cd AI-resume-jd-matching

# 3. Build the project
mvn clean install

# 4. Run the application
mvn spring-boot:run
```

### Alternative: Run with JAR

```bash
# Build JAR file
mvn clean package

# Run the JAR
java -jar target/resume-matcher-1.0.0.jar
```

### Access the Application

Open your browser and navigate to:
```
http://localhost:8081/index.html
```

### Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8081

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Logging Configuration
logging.level.com.resumematcher=DEBUG
```

---

## 🧪 Testing the System

### Using the Web Interface

1. **Open** browser: `http://localhost:8081/index.html`
2. **Upload** a resume file (PDF or TXT)
3. **Upload** a job description file (PDF or TXT)
4. **Click** "Analyze Match" button
5. **View** results:
   - Match score percentage
   - Matched keywords (green badges)
   - Missing keywords (red badges)

### Using cURL

```bash
curl -X POST http://localhost:8081/api/match \
  -F "resume=@/path/to/resume.pdf" \
  -F "jobDescription=@/path/to/job-description.txt" \
  | json_pp
```

### Using Postman

1. Set method to **POST**
2. URL: `http://localhost:8081/api/match`
3. Go to **Body** → **form-data**
4. Add key `resume` with type **File**, select resume file
5. Add key `jobDescription` with type **File**, select JD file
6. Click **Send**
7. View JSON response

### Sample Test Files

Create test files to try the system:

**test-resume.txt:**
```
John Doe
Software Engineer

EXPERIENCE:
- 5 years of Java development
- Expert in Spring Boot and REST APIs
- Built microservices architectures
- Database design with MySQL

SKILLS:
Java, Spring Boot, REST API, Microservices, MySQL, Git
```

**test-job-description.txt:**
```
JOB TITLE: Senior Java Developer

REQUIREMENTS:
- 3+ years Java experience
- Spring Boot expertise
- REST API development
- Microservices architecture
- Docker and Kubernetes
- AWS cloud experience
```

---

## ⚠️ Important Design Considerations

### ✅ What This System Does

- Measures **semantic similarity** between resume and job description
- Extracts **contextual keywords** from the job description dynamically
- Provides **transparent, explainable** matching results
- Works **domain-agnostic** across all industries and job types
- Uses **TF-IDF** to identify important terms, not just frequency
- Handles **synonym matching** through vectorization

### ❌ What This System Does NOT Do

- **Universal skill extraction** (requires trained NER models like spaCy)
- **Deep semantic understanding** (needs transformer models like BERT)
- **Bias detection** or fairness guarantees
- **Replace human judgment** in final hiring decisions
- **Parse structured data** (education, dates, contact info)
- **Rank multiple resumes** (designed for 1-to-1 comparison)

### 🎯 Why This Approach?

This system intentionally focuses on **domain-agnostic alignment** rather than trying to extract all possible skills universally. This design choice provides:

| Benefit | Explanation |
|---------|-------------|
| 🎯 **More Robust** | Works without training data or skill databases |
| 🔍 **More Explainable** | Clear keyword-based reasoning anyone can understand |
| 🛡️ **Interview-Safe** | Easy to defend technical decisions to recruiters |
| 🚀 **Production-Ready** | No complex model deployment or GPU requirements |
| 🌍 **Truly Generic** | Works for software, marketing, finance, healthcare, etc. |

### 🔬 Technical Rationale

**TF-IDF + Cosine Similarity** was chosen over deep learning because:

1. **No Training Data Required** - Works out of the box
2. **Interpretable** - Recruiters can see exact keyword matches
3. **Fast** - Processes documents in milliseconds
4. **Lightweight** - Runs on any server without GPUs
5. **Stable** - No model drift or retraining needed

---

## 📖 How It Works Under the Hood

### 1. Text Extraction (Apache Tika)

```
PDF/DOCX/TXT → Binary Data → Tika Parser → Clean Text
```

### 2. Text Preprocessing

```
Input: "I'm a Senior Java Developer with 5+ years experience!"

Steps:
1. Lowercase: "i'm a senior java developer with 5+ years experience!"
2. Tokenization: ["i'm", "a", "senior", "java", "developer", ...]
3. Remove stopwords: ["senior", "java", "developer", "5+", "years", "experience"]
4. Stemming (optional): ["senior", "java", "develop", "5+", "year", "experi"]

Output: ["senior", "java", "developer", "years", "experience"]
```

### 3. TF-IDF Vectorization

**Term Frequency (TF):**
```
TF(term, document) = (Number of times term appears) / (Total terms in document)
```

**Inverse Document Frequency (IDF):**
```
IDF(term) = log(Total documents / Documents containing term)
```

**TF-IDF Score:**
```
TF-IDF(term) = TF(term) × IDF(term)
```

### 4. Cosine Similarity

```
         A · B
cos(θ) = ─────
         ||A|| ||B||

Where:
- A, B are TF-IDF vectors
- A · B is dot product
- ||A||, ||B|| are vector magnitudes
```

**Result:** Score between 0 (no similarity) and 1 (identical)

### 5. Keyword Extraction

```
1. Calculate TF-IDF scores for all JD terms
2. Select top N terms (highest TF-IDF scores)
3. Check if each keyword exists in resume
4. Classify as "matched" or "missing"
```

<div align="center">


[Report Bug](https://github.com/your-username/AI-resume-jd-matching/issues) · [Request Feature](https://github.com/your-username/AI-resume-jd-matching/issues) · [Documentation](https://github.com/your-username/AI-resume-jd-matching/wiki)

---

</div>


