package lt.ltrp.business.dao.impl;

import lt.ltrp.dao.DaoException;
import lt.ltrp.job.dao.JobGateDao;
import lt.ltrp.job.object.Job;
import lt.ltrp.job.object.JobGate;
import lt.ltrp.job.object.JobRank;
import lt.ltrp.object.impl.JobGateImpl;
import net.gtaun.shoebill.data.Vector3D;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bebras
 *         2016.05.31.
 */
public class MySqlJobGateDaoImpl implements JobGateDao {

    private DataSource dataSource;

    public MySqlJobGateDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<JobGate> get(Job job) throws DaoException {
        List<JobGate> jobGates = new ArrayList<>();
        String sql = "SELECT * FROM job_gates WHERE job_id = ?";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, job.getUUID());
            ResultSet r = stmt.executeQuery();
            while(r.next())
                jobGates.add(toGate(r, job));
        } catch(SQLException e) {
            throw new DaoException("Could not retrieve gates for job " + job.getUUID(), e);
        }
        return jobGates;
    }

    @Override
    public void update(JobGate gate) throws DaoException {
        String sql = "UPDATE job_gates SET job_id = ?, rank_id = ?, model_id = ?, open_pos_x = ?, open_pos_y = ?, open_pos_z = ?, " +
                "open_rot_x = ?, open_rot_y = ?, open_rot_z = ?, closed_pos_x = ?, closed_pos_y = ?, closed_pos_z = ?, " +
                "closed_rot_x = ?, closed_rot_y = ?, closed_rot_z = ?, default_opened = ? WHERE id = ?";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, gate.getJob().getUUID());
            stmt.setInt(2, gate.getRank().getNumber());
            stmt.setInt(3, gate.getModelId());
            stmt.setFloat(4, gate.getOpenPosition().x);
            stmt.setFloat(5, gate.getOpenPosition().y);
            stmt.setFloat(6, gate.getOpenPosition().z);
            stmt.setFloat(7, gate.getOpenRotation().x);
            stmt.setFloat(8, gate.getOpenRotation().y);
            stmt.setFloat(9, gate.getOpenRotation().z);
            stmt.setFloat(10, gate.getClosedPosition().x);
            stmt.setFloat(11, gate.getClosedPosition().y);
            stmt.setFloat(12, gate.getClosedPosition().z);
            stmt.setFloat(13, gate.getClosedRotation().x);
            stmt.setFloat(14, gate.getClosedRotation().y);
            stmt.setFloat(15, gate.getClosedRotation().z);
            stmt.setBoolean(16, gate.isDefaultOpen());
            stmt.setInt(17, gate.getUUID());
            stmt.execute();
        } catch(SQLException e) {
            throw new DaoException("Can not update job gate " + gate.getUUID(), e);
        }
    }

    @Override
    public void remove(JobGate gate) throws DaoException {
        String sql = "DELETE FROM job_gates WHERE id = ?";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, gate.getUUID());
            stmt.execute();
        } catch(SQLException e) {
            throw new DaoException("Can not delete job gate " + gate.getUUID(), e);
        }
    }

    @Override
    public int insert(JobGate gate) throws DaoException {
        String sql = "INSERT INTO job_gates (job_id, rank_id, model_id, open_pos_x, open_pos_y, " +
                "open_pos_z, open_rot_x, open_rot_y, open_rot_z, closed_pos_x, closed_pos_y, " +
                "closed_pos_z, closed_rot_x, closed_rot_y, closed_rot_z, default_opened) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, gate.getJob().getUUID());
            stmt.setInt(2, gate.getRank().getNumber());
            stmt.setInt(3, gate.getModelId());
            stmt.setFloat(4, gate.getOpenPosition().x);
            stmt.setFloat(5, gate.getOpenPosition().y);
            stmt.setFloat(6, gate.getOpenPosition().z);
            stmt.setFloat(7, gate.getOpenRotation().x);
            stmt.setFloat(8, gate.getOpenRotation().y);
            stmt.setFloat(9, gate.getOpenRotation().z);
            stmt.setFloat(10, gate.getClosedPosition().x);
            stmt.setFloat(11, gate.getClosedPosition().y);
            stmt.setFloat(12, gate.getClosedPosition().z);
            stmt.setFloat(13, gate.getClosedRotation().x);
            stmt.setFloat(14, gate.getClosedRotation().y);
            stmt.setFloat(15, gate.getClosedRotation().z);
            stmt.setBoolean(16, gate.isDefaultOpen());
            stmt.execute();
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                return keys.getInt(1);
        } catch(SQLException e) {
            throw new DaoException("Can not insert job gate for job " + gate.getJob().getUUID(), e);
        }
        return 0;
    }

    private JobGate toGate(ResultSet r, Job job) throws SQLException {
        JobRank rank = job.getRankByUUID(r.getInt("rank_id"));
        return new JobGateImpl(r.getInt("uuid"),
                r.getInt("model_id"),
                new Vector3D(r.getFloat("open_pos_x"), r.getFloat("open_pos_y"), r.getFloat("open_pos_z")),
                new Vector3D(r.getFloat("open_rot_x"), r.getFloat("open_rot_y"), r.getFloat("open_rot_z")),
                new Vector3D(r.getFloat("closed_pos_x"), r.getFloat("closed_pos_y"), r.getFloat("closed_pos_z")),
                new Vector3D(r.getFloat("closed_rot_x"), r.getFloat("closed_rot_y"), r.getFloat("closed_rot_z")),
                r.getFloat("speed"),
                job,
                rank,
                r.getBoolean("default_opened")
        );
    }
}
