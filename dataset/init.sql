SELECT 'CREATE DATABASE innotice'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'innotice')\gexec