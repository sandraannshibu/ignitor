from flask import * 
from database import*





import json
from web3 import Web3, HTTPProvider

# truffle development blockchain address
blockchain_address = ' http://127.0.0.1:9545'
# Client instance to interact with the blockchain
web3 = Web3(HTTPProvider(blockchain_address))
# Set the default account (so we don't need to set the "from" for every transaction call)
web3.eth.defaultAccount = web3.eth.accounts[0]
compiled_contract_path = 'D:/Ignitor/node_modules/.bin/build/contracts/Evuniversal.json'
# compiled_contract_path = 'F:/NGO/node_modules/.bin/build/contracts/medicines.json'
# Deployed contract address (see `migrate` command output: `contract address`)
deployed_contract_address = '0x247a5204D4fB00775e80259A4777F4adDBE89800'


admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():

	return render_template('admin_home.html')

@admin.route('/admin_managemechanics',methods=['post','get'])
def admin_managemechanics():

	data={}
	q="select * from mechanic  inner join login using (login_id) where usertype='pending' or usertype='Mechanic'"
	res=select(q)
	data['mechanicview']=res


	if "action" in request.args:
		action=request.args['action']
		mid=request.args['mid']

	else:action=None

	if action=='accept':
		# q="delete from mechanic where mechanic_id='%s'"%(mid)
		# delete(q)

		q="update login set usertype='Mechanic'  where login_id='%s'"%(mid)
		update(q)
		flash('Accepted successfully')
		return redirect(url_for('admin.admin_managemechanics'))


	if action=='reject':

		q="update login set usertype='block'  where login_id='%s'"%(mid)
		update(q)
	# 	q="select * from mechanic where mechanic_id='%s'"%(mid)
	# 	res=select(q)
	# 	data['mechanicupdate']=res

	# 	if "upset" in request.form:
	# 		f=request.form['fname']
	# 		l=request.form['lname']
	# 		p=request.form['place']
	# 		ph=request.form['phone']
	# 		e=request.form['email']
	# 		q="update mechanic set firstname='%s' ,lastname='%s',place='%s',phone='%s',email='%s' where mechanic_id='%s'"%(f,l,p,ph,e,mid)
	# 		update(q)
	# 		print(q)
	# 		flash('successfully')
	# 		return redirect(url_for('admin.admin_managemechanics'))
			

	# if "mechanic" in request.form:
	# 	f=request.form['fname']
	# 	l=request.form['lname']
	# 	pla=request.form['place']
	# 	pho=request.form['phone']
	# 	e=request.form['email']
	# 	la=request.form['lat']
	# 	lo=request.form['lon']
	# 	un=request.form['uname']
	# 	p=request.form['pwd']


	# 	q="insert into login values(null,'%s','%s','Mechanic')"%(un,p)
	# 	id=insert(q)
	# 	q="insert into mechanic values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(id,f,l,pla,pho,e,la,lo)
	# 	insert(q)
		flash(' Rejected successfully')
		return redirect(url_for('admin.admin_managemechanics'))

	return render_template('admin_managemechanics.html',data=data)

@admin.route('/admin_viewcomplaint')
def admin_viewcomplaint():
	data={}
	q="select * from complaint  inner join mechanic on mechanic.login_id=complaint.user_id"
	res=select(q)
	data['mechanicview']=res



	
	q="select * from complaint  inner join user on user.login_id=complaint.user_id"
	res=select(q)
	data['userview']=res



	q="select * from complaint  inner join bunk on bunk.login_id=complaint.user_id"
	res=select(q)
	data['bunkview']=res


	
	q="select * from complaint  inner join sparepart on sparepart.login_id=complaint.user_id"
	res=select(q)
	data['sparepartview']=res






	return render_template('admin_viewcomplaint.html',data=data)



@admin.route('/admin_viewpayment')
def admin_viewpayment():

	data={}
	with open(compiled_contract_path) as file:
		contract_json = json.load(file)  # load contract info as JSON
		contract_abi = contract_json['abi']  # fetch contract's abi - necessary to call its functions
	contract = web3.eth.contract(address=deployed_contract_address, abi=contract_abi)
	blocknumber = web3.eth.get_block_number()
	res = []
	try:
		for i in range(blocknumber, 0, -1):
			a = web3.eth.get_transaction_by_block(i, 0)
			decoded_input = contract.decode_function_input(a['input'])
			print(decoded_input)
			if str(decoded_input[0]) == "<Function add_payment(uint256,uint256,string,string,string,string)>":
				# if int(decoded_input[1]['u_id']) == int(session['user_id']):
					res.append(decoded_input[1])

					
					# if decoded_input[1]['passportnum']==pno:
					# 	flash("verified")

	except Exception as e:
		print("", e)
	print(res)

	data['paymentview']=res
	return render_template('admin_viewpayment.html',data=data)


@admin.route('/admin_viewrating')
def admin_viewrating():
	data={}
	q="select * from rating inner join user using (user_id)"
	res=select(q)
	data['ratingview']=res

	return render_template('admin_viewrating.html',data=data)



@admin.route('/admin_viewspareparts')
def admin_viewspareparts():
	data={}
	q="select * from sparepart inner join login using (login_id)"
	res=select(q)
	data['sparepartsview']=res

	if "action" in request.args:
		action=request.args['action']
		lid=request.args['lid']

	else:action=None

	if action=='accept':
		q="update login set usertype='Deliveryboy' where login_id='%s'"%(lid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_viewspareparts'))


	if action=='reject':
		q="update login set usertype='Reject' where login_id='%s'"%(lid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_viewspareparts'))
		
		
		
	return render_template('admin_viewspareparts.html',data=data)




@admin.route('/admin_viewuser')
def admin_viewuser():
	data={}
	q="select * from user"
	res=select(q)
	data['userview']=res

	return render_template('admin_viewuser.html',data=data)


@admin.route('/admin_viewproduct')
def admin_viewproduct():
	data={}
	sid=request.args['sid']
	q="select * from product where sparepart_id='%s'"%(sid)
	res=select(q)
	data['userview']=res

	return render_template('admin_viewproduct.html',data=data)

@admin.route('/admin_sendreply',methods=['post','get'])
def admin_sendreply():

	if "sendreply" in request.form:
		cid=request.args['cid']
		reply=request.form['reply']
		q="update complaint set reply='%s' where complaint_id='%s'"%(reply,cid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_viewcomplaint'))


	return render_template('admin_sendreply.html')
	

