select
	si.*,
	((si.price_per_unit - si.provider_price) * pc.quantity ) as profit,
	pc.quantity,
	FROM_UNIXTIME(s.created_date/1000) as created_datetime,
       DATE(FROM_UNIXTIME(s.created_date/1000)) as created_date,
	p.name,
	p.brand
from
	sale_item si
inner join (
	select
		SUM(quantity) as quantity,
		item_id
	from
		product_consumed pc
	GROUP BY
		pc.item_id ) pc on
	si.id = pc.item_id
inner join sale s on
	si.sale_id = s.id
inner join product p on
	si.product_id = p.id