-- 코드를 입력하세요
select a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(b.PRICE * s.SALES) as TOTAL_SALES
from BOOK b join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID join 
BOOK_SALES s on b.BOOK_ID = s.BOOK_ID 
where substr(s.SALES_DATE, 1, 7) = '2022-01'
group by a.AUTHOR_NAME, b.CATEGORY
order by b.AUTHOR_ID asc, b.CATEGORY desc;
