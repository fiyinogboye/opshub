# Make OpsHub genuinely yours

This codebase is a working reference implementation, not a shortcut around learning. Use the plan below to turn it into a project you can defend confidently.

## Week 1 — Understand it

- Run the app and test every account.
- Trace one `POST /api/incidents` request through controller → service → repository → database.
- Draw the two database tables yourself and explain the relationship.
- Change the colours and sample data without assistance.

## Week 2 — Rebuild one slice

- Create a new branch.
- Delete and independently rebuild the status-update feature.
- Add tests for `WAITING` and `RESOLVED`.
- Open a pull request to your own repository and explain the change in writing.

## Week 3 — Add your feature

Recommended: **shift handoffs**.

- Add `Shift` and `HandoffNote` entities.
- Let staff create a handoff note attached to a building and shift.
- Allow the next staff member to acknowledge it.
- Add overdue/unacknowledged counts to the dashboard.
- Write the migration and tests before changing the interface.

## Week 4 — Deploy and present

- Replace demo passwords with environment-based accounts or database users.
- Deploy the app and PostgreSQL.
- Record a 60–90 second walkthrough.
- Add the live link, repository, screenshots, and architecture notes to the README.
- Practise the interview questions below aloud.

## Interview questions you must be able to answer

1. What problem does OpsHub solve, and why did you choose it?
2. Why is the service method transactional?
3. Where is authorization enforced, and why is hiding a button insufficient?
4. How does the filter query become a database query?
5. What is CSRF and how does this application mitigate it?
6. How would you handle 100,000 incidents instead of 100?
7. What would you change before real users and sensitive data were added?
8. Describe a bug you found while building or extending it.

## Application schedule

Do not wait four weeks to apply. Start now with your existing experience and list OpsHub as **in development** until you can run and explain it. Aim for consistent applications and referrals while improving the project in parallel.
