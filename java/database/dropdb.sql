-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'ladybookhouse';

DROP DATABASE ladybookhouse;

DROP USER ladybookhouse_owner;
DROP USER ladybookshouse_appuser;
