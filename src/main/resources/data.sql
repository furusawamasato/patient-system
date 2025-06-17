INSERT INTO lists (list_id, name, latestBookingDate, place) VALUES
  (1, '豊臣秀吉', '2025-06-02', '診察室'),
  (2, '織田信長', '2025-06-02', '検査室'),
  (3, '徳川家康', '2025-06-03', '治療室'),
  (4, '武田信玄', '2025-06-02', '呼吸器科');

INSERT INTO patients (list_id, name, age, bloodType, diseaseName, symptoms, latestBookingDate, place) VALUES
  (1, '豊臣秀吉', 61, 'A', '風邪', '発熱、喉の痛み', '2025-06-02', '診察室'),
  (2, '織田信長', 47, 'O', '胃腸炎', '下痢、腹痛', '2025-06-02', '検査室'),
  (3, '徳川家康', 73, 'AB', '風邪', '発熱、喉の痛み', '2025-06-03', '治療室'),
  (4, '武田信玄', 45, 'A', '喘息', '呼吸困難', '2025-06-02', '呼吸器科');

INSERT INTO users (username, password) VALUES
  ('sample', '$2a$08$hBocxV325GV9RKzHR.lHreJr8DsMrkg6vg0kRTbNnBvUFknu670GC');
