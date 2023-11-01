-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER ladybookhouse_owner
WITH PASSWORD 'ladybookhouse';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO ladybookhouse_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO ladybookhouse_owner;

CREATE USER ladybookhouse_appuser
WITH PASSWORD 'ladybookhouse';


GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO ladybookhouse_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO ladybookhouse_appuser;
