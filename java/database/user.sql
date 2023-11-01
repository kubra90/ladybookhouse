-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER bookstore_app_owner
WITH PASSWORD 'bookstoreapp';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO bookstore_app_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO bookstore_app_owner;

CREATE USER bookstore_app_appuser
WITH PASSWORD 'bookstoreapp';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO bookstore_app_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO bookstore_app_appuser;
