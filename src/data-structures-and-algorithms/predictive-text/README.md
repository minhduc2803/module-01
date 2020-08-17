# Predictive Text

Cho dataset [Blog Authorship Corpus](http://u.cs.biu.ac.il/~koppel/BlogCorpus.htm).
Làm chương trình Java cung cấp 2 tính năng:

- Kiểm tra 1 từ có nằm trong dataset không?
- Gợi ý những từ giống với từ input mà có trong dataset (gần giống như cách Google instant search gợi ý). Sự gần giống giữa các từ do em tự định nghĩa nhưng phải hợp lý.

Yêu cầu:

- Phải hiện thực ít nhất 2 cách với tính năng kiểm tra từ tồn tại bằng cách `implement` từ interface [Dictionary](src/main/java/vn/com/zalopay/syllabus/predictive/service/Dictionary.java)
- Tìm cách tối ưu chương trình với các cấu trúc dữ liệu mà em đã học được ở phần lý thuyết.
- `Benchmark` cho phần kiểm tra từ bằng JMH. File `Main` đã có sẵn ở [BenchmarkMain](src/main/java/vn/com/zalopay/syllabus/predictive/benchmark/BenchmarkMain.java) 
- Không được import lib có sẵn (được copy source hiện thực nhưng sẽ có hỏi về cách hoạt động).
- Phần gợi ý phải gợi ý ít nhất 5 từ gần giống với input đầu vào.
- Tự định nghĩa cách hiện output.
- Và hãy Code như một Good Programmer

