### Subquery

* 서브쿼리는 쿼리안에 존재하는 또 다른 쿼리를 말한다.
* 서브쿼리는 select절,from절,where절에 올 수 있다.
* from절에 오는 서브쿼리를 인라인뷰라고 부른다.
* 서브쿼리가 단 하나의 행만 반환하는 경우를 스칼라 서브쿼리라고 부른다.
* 스칼라 서브쿼리는 칼럼갯수에따라 select절이나 where절에 올 수 있다.
* 서브쿼리를 실행하는데 바깥쪽에 쿼리에 값이 이용되는 경우를 상관서브 쿼리라고 부른다
* 바깥쪽 서브쿼리와 관계없이 실행되는 서브쿼리를 단독 서브쿼리라고 부른다.
* 서브쿼리는 종류나 위치에따라 성능이 떨어질 수 있다. 이 경우 서브쿼리를 join으로 바꾸거나, 쿼리를 나눠서 실행하면 해결할 수 있다.
------