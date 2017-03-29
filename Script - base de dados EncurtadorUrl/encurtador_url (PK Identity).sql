CREATE TABLE [dbo].[ENCURTADOR_URL](
	[EncurtadorUrlId] [bigint] primary key NOT NULL,
	[Url] [nvarchar](255) NOT NULL,
	[Alias] [nvarchar](255) NOT NULL,
	[DescriptionReturn] [nvarchar](255) NOT NULL,
	[CodeReturn] [int] NOT NULL,
	[Ativo] [bit] NOT NULL
)

GO
alter table [ENCURTADOR_URL] add unique ([EncurtadorUrlId],[Url], [Alias])
GO
