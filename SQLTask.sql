1.  get all statuses, not repeating, alphabetically ordered

  SELECT T.STATUS_ID FROM TASK T GROUP BY T.STATUS_ID ORDER BY T.STATUS_ID;
	
2.  get the count of all tasks in each project, order by tasks count descending
	
	SELECT T.PROJECT_ID, COUNT(T.TASK_ID) C FROM TASK T GROUP BY T.PROJECT_ID ORDER BY C DESC;

3.  get the count of all tasks in each PROJECT, order by projects names

	SELECT T.PROJECT_ID, P.PROJECT_NAME n, COUNT(T.TASK_ID) C FROM PROJECT P, TASK T 
	WHERE P.PROJECT_ID = T.PROJECT_ID
	GROUP BY P.PROJECT_ID 
	ORDER BY n;
	
4   get the tasks for all projects having the name beginning with “N” letter

	SELECT * FROM TASK T WHERE T.PROJECT_ID 
	IN (SELECT P.PROJECT_ID FROM PROJECT P WHERE P.PROJECT_NAME LIKE 'N%');

5   get the list of all projects containing the ‘a’ letter in the middle of the name,
	and show the tasks count near each project. 
	Mention that there can exist projects without tasks and tasks with project_id=NULL
	
	SELECT P.PROJECT_NAME, P.PROJECT_ID, COUNT(T.TASK_ID) FROM PROJECT AS P  
	LEFT OUTER JOIN TASK T ON P.PROJECT_ID = T.PROJECT_ID 
	WHERE P.PROJECT_ID 
	IN (SELECT PROJECT.PROJECT_ID FROM PROJECT WHERE PROJECT.PROJECT_NAME LIKE '%a%')
	GROUP BY P.PROJECT_ID;

 6  get the list of tasks with duplicate names. Order alphabetically
	
	SELECT * FROM TASK T, 
	(SELECT T.TASK_NAME n, COUNT(T.TASK_NAME) CNT FROM TASK T
	GROUP BY n HAVING CNT > 1) innerSelect
	WHERE T.TASK_NAME LIKE innerSeleCT.n
	GROUP BY T.TASK_ID 
	ORDER BY T.TASK_NAME;
 
 7  get the list of tasks having several exact matches of both name and status, from the project ‘Garage’.
	Order by matches count
	
 
 8  get the list of project names having more than 10 tasks in status ‘completed’. Order by project_id
	
	SELECT P.PROJECT_NAME FROM PROJECT P
	WHERE P.PROJECT_ID IN (SELECT T.PROJECT_ID FROM TASK T, STATUS S 
	WHERE S.STATUS_ID = T.STATUS_ID AND S.STATUS_NAME = 'DONE' 
	GROUP BY T.PROJECT_ID
	HAVING COUNT(T.PROJECT_ID) > 10);
