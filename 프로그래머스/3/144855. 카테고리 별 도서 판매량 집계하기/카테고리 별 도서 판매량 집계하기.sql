-- 코드를 입력하세요
select b.CATEGORY, sum(s.SALES) as TOTAL_SALES
from Book b join BOOK_SALES s on b.BOOK_ID = s.BOOK_ID
where substr(s.SALES_DATE, 1, 7) = '2022-01'
group by b.CATEGORY
order by b.CATEGORY;